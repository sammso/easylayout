package com.sohlman.easylayout;

/**
 * @author Sampsa Sohlman
/*
 * Version: 17.11.2003
 *
 */
public class Constraint extends Position
{
	
	/**
	 * @param ai_column
	 * @param ai_row
	 * @param ai_columnSpan
	 * @param ai_rowSpan
	 * @param ai_hAligment
	 * @param ai_vAligment
	 * @param ai_hGap
	 * @param ai_vGap
	 */
	public Constraint(int ai_column, int ai_row, int ai_columnSpan, int ai_rowSpan, int ai_hAligment, int ai_vAligment, int ai_hGap, int ai_vGap)
	{
		super(ai_column, ai_row, ai_columnSpan, ai_rowSpan, ai_hAligment, ai_vAligment, ai_hGap, ai_vGap);
	}

	/**
	 * @param ai_column
	 * @param ai_row
	 * @param ai_columnSpan
	 * @param ai_rowSpan
	 */
	public Constraint(int ai_column, int ai_row, int ai_columnSpan, int ai_rowSpan)
	{
		super(ai_column, ai_row, ai_columnSpan, ai_rowSpan);

	}

	/**
	 * @param ai_column
	 * @param ai_row
	 */
	public Constraint(int ai_column, int ai_row)
	{
		super(ai_column, ai_row);
	}

	/**
	 * @param ai_column
	 * @param ai_row
	 * @param ai_hAligment
	 * @param ai_vAligment
	 * @param ai_hGap
	 * @param ai_vGap
	 */
	public Constraint(int ai_column, int ai_row, int ai_hAligment, int ai_vAligment, int ai_hGap, int ai_vGap)
	{
		super(ai_column, ai_row, ai_hAligment, ai_vAligment, ai_hGap, ai_vGap);
	}

}
