package com.sohlman.easylayout;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager2;
import java.util.Hashtable;
import java.util.Vector;

/**
 * 
 * @author Sampsa Sohlman
 * @version 6.5.2003
 */
public class EasyLayout implements LayoutManager2, java.io.Serializable
{
	private boolean ib_calculationDone = false;

	private int[] ii_rows;
	private int[] ii_columns;

	private int[] ii_origColumnWidths;
	private int[] ii_origRowHeights;
	private boolean ib_generateColumnWidths;
	private boolean ib_generateRowHeights;

	private int ii_vGap = 5;
	private int ii_hGap = 5;

	private int ii_origWidth;
	private int ii_origHeight;

	private int[] ii_origXPositions;
	private int[] ii_origYPositions;

	private Constraint i_Constraint_Default = new Constraint(0, 0, 1, 1);

	private Vector iVe_Components = new Vector();

	public EasyLayout(int[] ai_origColumnWidths, int[] ai_origRowHeights, int[] ai_columns, int[] ai_rows)
	{
		int li_count = 0;
		for (int li_index = 0; li_index < ai_columns.length; li_index++)
		{
			li_count += ai_columns[li_index];

		}
		if (li_count != 100)
		{
			throw new IllegalArgumentException("Sum of column percentagehas to be 100");
		}
		li_count = 0;
		for (int li_index = 0; li_index < ai_rows.length; li_index++)
		{
			li_count += ai_rows[li_index];

		}
		if (li_count != 100)
		{
			throw new IllegalArgumentException("Sum of row percentage has to be 100");
		}

		ii_rows = ai_rows;
		ii_columns = ai_columns;
		if (ai_origColumnWidths != null)
		{
			ii_origColumnWidths = ai_origColumnWidths;
			ib_generateColumnWidths = false;
		}
		else
		{
			ii_origColumnWidths = new int[ai_columns.length];
			ib_generateColumnWidths = true;
		}

		if (ai_origRowHeights != null)
		{
			ii_origRowHeights = ai_origRowHeights;
			ib_generateRowHeights = false;
		}
		else
		{
			ii_origRowHeights = new int[ai_rows.length];
			ib_generateRowHeights = true;
		}

		ii_origXPositions = new int[ai_columns.length];
		ii_origYPositions = new int[ai_rows.length];
	}

	public EasyLayout(int[] ai_columns, int[] ai_rows)
	{
		this(null, null, ai_columns, ai_rows);
	}

	/**
	 * @see java.awt.LayoutManager2#addLayoutComponent(java.awt.Component, java.lang.Object)
	 */
	public void addLayoutComponent(Component a_Component, Object aO_Constraint)
	{
		//System.out.println("addLayoutComponent Component : " + a_Component.getMinimumSize());

		if (aO_Constraint == null)
		{
			aO_Constraint = i_Constraint_Default;
		}
		if (aO_Constraint instanceof Constraint)
		{
			Constraint l_Constraint = (Constraint)aO_Constraint;
			if (l_Constraint.hGap == Constraint.DEFAULT)
			{
				l_Constraint.hGap = ii_hGap;
			}
			if (l_Constraint.vGap == Constraint.DEFAULT)
			{
				l_Constraint.vGap = ii_vGap;
			}

			l_Constraint.component = a_Component;
			iVe_Components.add(l_Constraint);
			ib_calculationDone = false;
		}
		else
		{
			throw new IllegalArgumentException("cannot add to layout: constraints must be a com.sohlman.rlm.Constraint");
		}
	}

	/**
	 * @see java.awt.LayoutManager2#getLayoutAlignmentX(java.awt.Container)
	 */
	public float getLayoutAlignmentX(Container a_Component)
	{
		//System.out.println("public float getLayoutAlignmentX(Container a_Component)");
		return 0;
	}

	/**
	 * @see java.awt.LayoutManager2#getLayoutAlignmentY(java.awt.Container)
	 */
	public float getLayoutAlignmentY(Container a_Container)
	{
		//System.out.println("public float getLayoutAlignmentY(Container a_Container)");
		return 0;
	}

	/**
	 * @see java.awt.LayoutManager2#invalidateLayout(java.awt.Container)
	 */
	public void invalidateLayout(Container a_Container)
	{
		//System.out.println("public void invalidateLayout(Container a_Container)");
		preCalculate(a_Container);

	}

	/**
	 * @see java.awt.LayoutManager2#maximumLayoutSize(java.awt.Container)
	 */
	public Dimension maximumLayoutSize(Container a_Container)
	{
		//System.out.println("public Dimension maximumLayoutSize(Container a_Container)");
		preCalculate(a_Container);
		return null;
	}

	/**
	 * @see java.awt.LayoutManager#addLayoutComponent(java.lang.String, java.awt.Component)
	 */
	public void addLayoutComponent(String a_String, Component a_Component)
	{
		//System.out.println("public void addLayoutComponent(String a_String, Component a_Component)");
	}

	/**
	 * @see java.awt.LayoutManager#layoutContainer(java.awt.Container)
	 */
	public void layoutContainer(Container a_Container)
	{
		preCalculate(a_Container);
		synchronized (a_Container.getTreeLock())
		{
			Dimension l_Dimension = a_Container.getSize();

			int li_xAdd = l_Dimension.width - ii_origWidth;
			int li_yAdd = l_Dimension.height - ii_origHeight;

			for (int li_index = 0; li_index < iVe_Components.size(); li_index++)
			{
				Constraint l_Constraint = (Constraint)iVe_Components.get(li_index);
				layoutComponent(l_Constraint, li_xAdd, li_yAdd);
			}
		}
	}

	/**
	 * @see java.awt.LayoutManager#minimumLayoutSize(java.awt.Container)
	 */
	public Dimension minimumLayoutSize(Container a_Container)
	{
		preCalculate(a_Container);
		synchronized (a_Container.getTreeLock())
		{
			//System.out.println("public Dimension minimumLayoutSize(Container a_Container)");
			return new Dimension(ii_origWidth, ii_origHeight);
		}
	}

	/**
	 * @see java.awt.LayoutManager#preferredLayoutSize(java.awt.Container)
	 */
	public Dimension preferredLayoutSize(Container a_Container)
	{
		preCalculate(a_Container);
		synchronized (a_Container.getTreeLock())
		{
			//System.out.println("public Dimension preferredLayoutSize(Container a_Container)");
			return new Dimension(ii_origWidth, ii_origHeight);
		}

	}

	/**
	 * @see java.awt.LayoutManager#removeLayoutComponent(java.awt.Component)
	 */
	public void removeLayoutComponent(Component a_Component)
	{
		//System.out.println("public void removeLayoutComponent(Component a_Component)");
		iVe_Components.remove(a_Component);
		ib_calculationDone = false;

	}

	private void preCalculate(Container a_Container)
	{
		if (!ib_calculationDone)
		{
			// Calculate Box sizes in Grid
			int[] li_columnPercentages = new int[ii_columns.length];
			int[] li_rowPercentages = new int[ii_rows.length];

			for (int li_index = 0; li_index < iVe_Components.size(); li_index++)
			{
				Constraint l_Constraint = (Constraint)iVe_Components.get(li_index);
				Component l_Component = l_Constraint.component;
				//			Dimension l_Dimension = l_Component.getMinimumSize();
				Dimension l_Dimension = l_Component.getPreferredSize();
				//System.out.println(l_Component + "\n  " +l_Dimension);
				l_Constraint.origWidth = l_Dimension.width;
				l_Constraint.origHeight = l_Dimension.height;

				if (ib_generateColumnWidths && l_Constraint.columnSpan == 1)
				{
					if (ii_origColumnWidths[l_Constraint.column] < l_Dimension.width)
					{
						ii_origColumnWidths[l_Constraint.column] = l_Dimension.width + (2 * l_Constraint.hGap);
					}
				}
				if (ib_generateRowHeights && l_Constraint.rowSpan == 1)
				{
					if (ii_origRowHeights[l_Constraint.row] < l_Dimension.height)
					{
						ii_origRowHeights[l_Constraint.row] = l_Dimension.height + (2 * l_Constraint.vGap);
					}
				}
			}

			// Calculate original width and height

			int li_tmpC = 0;
			int li_tmpP = 0;
			for (int li_index = 0; li_index < ii_origColumnWidths.length; li_index++)
			{
				li_tmpP += ii_columns[li_index];
				li_columnPercentages[li_index] = li_tmpP;
				ii_origXPositions[li_index] = li_tmpC;
				li_tmpC += ii_origColumnWidths[li_index];
			}
			ii_origWidth = li_tmpC;
			li_tmpC = 0;
			li_tmpP = 0;
			for (int li_index = 0; li_index < ii_origRowHeights.length; li_index++)
			{
				li_tmpP += ii_rows[li_index];
				li_rowPercentages[li_index] = li_tmpP;
				ii_origYPositions[li_index] = li_tmpC;
				li_tmpC += ii_origRowHeights[li_index];
			}

			ii_origHeight = li_tmpC;
			//System.out.println(" OrigSize " + ii_origWidth + "," + ii_origHeight);

			// Calculate Resize Percentages to all components

			for (int li_index = 0; li_index < iVe_Components.size(); li_index++)
			{
				Constraint l_Constraint = (Constraint)iVe_Components.get(li_index);
				l_Constraint.origX = ii_origXPositions[l_Constraint.column];
				l_Constraint.origY = ii_origYPositions[l_Constraint.row];
				if (l_Constraint.column > 0)
				{
					l_Constraint.xPersentage = li_columnPercentages[l_Constraint.column - 1];
				}
				if (l_Constraint.row > 0)
				{
					l_Constraint.yPersentage = li_rowPercentages[l_Constraint.row - 1];
				}

				l_Constraint.gridOrigWidth = 0;
				l_Constraint.widthPersentage = 0;

				Dimension l_Dimension = l_Constraint.component.getPreferredSize();

				for (int li_i = l_Constraint.column; li_i < l_Constraint.column + l_Constraint.columnSpan; li_i++)
				{
					l_Constraint.widthPersentage += ii_columns[li_i];
					if (l_Constraint.hAligment == Constraint.FULL)
					{
						l_Constraint.gridOrigWidth += ii_origColumnWidths[li_i];
					}
					else
					{
						l_Constraint.gridOrigWidth = l_Dimension.width;
					}

				}

				l_Constraint.heightPersentage = 0;
				l_Constraint.gridOrigHeight = 0;

				for (int li_i = l_Constraint.row; li_i < l_Constraint.row + l_Constraint.rowSpan; li_i++)
				{
					l_Constraint.heightPersentage += ii_rows[li_i];
					if (l_Constraint.hAligment == Constraint.FULL)
					{
						l_Constraint.gridOrigHeight += ii_origRowHeights[li_i];
					}
					else
					{
						l_Constraint.gridOrigHeight = l_Dimension.height;
					}
				}

			}
		}
	}

	private void layoutComponent(Constraint a_Constraint, int ai_xAdd, int ai_yAdd)
	{
		int li_newX = a_Constraint.origX;
		int li_newY = a_Constraint.origY;
		int li_newWidth = a_Constraint.gridOrigWidth;
		int li_newHeight = a_Constraint.gridOrigHeight;

		if (a_Constraint.xPersentage > 0)
		{
			li_newX = a_Constraint.origX + (ai_xAdd * a_Constraint.xPersentage) / 100;
		}
		if (a_Constraint.yPersentage > 0)
		{
			li_newY = a_Constraint.origY + (ai_yAdd * a_Constraint.yPersentage) / 100;
		}

		if (a_Constraint.widthPersentage > 0)
		{
			li_newWidth = a_Constraint.gridOrigWidth + (ai_xAdd * a_Constraint.widthPersentage) / 100;
		}

		if (a_Constraint.heightPersentage > 0)
		{
			li_newHeight = a_Constraint.gridOrigHeight + (ai_yAdd * a_Constraint.heightPersentage) / 100;
		}

		li_newX += a_Constraint.hGap;
		li_newY += a_Constraint.vGap;
		li_newWidth -= (2 * a_Constraint.hGap);
		li_newHeight -= (2 * a_Constraint.vGap);
		int li_x;
		switch (a_Constraint.hAligment)
		{
			case Constraint.LEFT :
				if (li_newHeight > a_Constraint.origWidth)
				{
					li_newWidth = a_Constraint.origWidth;
				}
				break;
			case Constraint.RIGHT :
				if (li_newHeight > a_Constraint.origWidth)
				{
					li_newX = li_newX + li_newWidth - a_Constraint.origWidth;
					li_newWidth = a_Constraint.origWidth;
				}
				break;
			case Constraint.CENTER :
				//FIX
				li_x = li_newX + (li_newWidth - a_Constraint.origWidth) / 2;
				if (li_x > li_newX)
				{
					li_newX = li_x;
				}
				if (li_newHeight > a_Constraint.origWidth)
				{
					li_newWidth = a_Constraint.origWidth;
				}
				break;
			default : // Constraint.FULL
		}
		int li_y;
		switch (a_Constraint.vAligment)
		{
			case Constraint.TOP :
				if (li_newHeight > a_Constraint.origHeight)
				{
					li_newHeight = a_Constraint.origHeight;
				}
				break;
			case Constraint.BOTTOM :
				li_y = li_newY + li_newHeight - a_Constraint.origHeight;
				if (li_y > li_newY)
				{
					li_newY = li_y;
				}
				if (li_newHeight > a_Constraint.origHeight)
				{
					li_newHeight = a_Constraint.origHeight;
				}
				break;
			case Constraint.CENTER :
				// FIX
				li_y = li_newY + (li_newHeight - a_Constraint.origHeight) / 2;
				if (li_y > li_newY)
				{
					li_newY = li_y;
				}
				if (li_newHeight > a_Constraint.origHeight)
				{
					li_newHeight = a_Constraint.origHeight;
				}
				break;
			default : // Constraint.FULL
		}

		a_Constraint.component.setBounds(li_newX, li_newY, li_newWidth, li_newHeight);
	}
}
