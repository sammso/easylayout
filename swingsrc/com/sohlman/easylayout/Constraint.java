package com.sohlman.easylayout;

import java.awt.Component;
import java.io.Serializable;

/**
 * @author Sampsa Sohlman
 * @version 9.5.2003
 */
public class Constraint implements Serializable
{
	public final static int FULL = 1;
	public final static int LEFT = 2;
	public final static int TOP = 2;
	public final static int RIGHT = 3;
	public final static int BOTTOM = 3;
	public final static int CENTER = 4;
	public final static int NOGAP = -1;

	int column;
	int row;
	int columnSpan;
	int rowSpan;
	int xPersentage;
	int yPersentage;
	int widthPersentage;
	int heightPersentage;
	int origHeight;
	int origWidth;
	int origX;
	int origY;
	int hAligment;
	int vAligment;
	int hGap;
	int vGap;

	Component component = null;

	/**
	 * 
	 */
	public Constraint(int ai_column, int ai_row, int ai_columnSpan, int ai_rowSpan, int ai_hAligment, int ai_vAligment, int ai_hGap, int ai_vGap)
	{
		this.column = ai_column;
		this.row = ai_row;
		this.columnSpan = ai_columnSpan;
		this.rowSpan = ai_rowSpan;
		this.hAligment = ai_hAligment;
		this.vAligment = ai_vAligment;
		this.hGap = ai_hGap;
		this.vGap = ai_vGap;
	}

	public Constraint(int ai_column, int ai_row, int ai_columnSpan, int ai_rowSpan)
	{
		this(ai_column, ai_row, ai_columnSpan, ai_rowSpan, FULL, FULL, NOGAP, NOGAP);
	}

	public String toString()
	{
		return this.getClass().getName()
			+ "[column="
			+ this.column
			+ ",row="
			+ this.row
			+ ",columnSpan="
			+ this.columnSpan
			+ ",rowSpan="
			+ this.rowSpan
			+ ",origX="
			+ this.origX
			+ ",origY="
			+ this.origY
			+ ",origWidth="
			+ this.origWidth
			+ ",origHeight="
			+ this.origHeight
			+ ",xPersentage="
			+ this.xPersentage
			+ ",yPersentage="
			+ this.yPersentage
			+ ",widthPersentage="
			+ this.widthPersentage
			+ ",heightPersentage="
			+ this.heightPersentage
			+ ",heightPersentage="
			+ this.hGap
			+ ",hGap="
			+ this.vGap
			+ ",vGap="
			+ this.component.toString()
			+ "]";
	}

	/**
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object a_Object)
	{
		return component == a_Object;
	}
}
