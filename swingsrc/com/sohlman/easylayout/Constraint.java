package com.sohlman.easylayout;

/**
 * @author Sampsa Sohlman
/*
 * This is still here for backward compatiblity.
 * 
 * Version: 17.11.2003
 *
 */
public class Constraint extends Position
{
	
	/**
	 * @see Position#Position(int, int, int, int, int, int, int, int) 
	 */
	public Constraint(int ai_column, int ai_row, int ai_columnSpan, int ai_rowSpan, int ai_hAligment, int ai_vAligment, int ai_hGap, int ai_vGap)
	{
		super(ai_column, ai_row, ai_columnSpan, ai_rowSpan, ai_hAligment, ai_vAligment, ai_hGap, ai_vGap);
	}

	/**
	 * @see Position#Position(int, int, int, int) 
	 */
	public Constraint(int ai_column, int ai_row, int ai_columnSpan, int ai_rowSpan)
	{
		super(ai_column, ai_row, ai_columnSpan, ai_rowSpan);

	}

	/**
	 * @see Position#Position(int, int) 
	 */
	public Constraint(int ai_column, int ai_row)
	{
		super(ai_column, ai_row);
	}

	/**
	 * @see Position#Position(int, int, int, int, int, int) 
	 */
	public Constraint(int ai_column, int ai_row, int ai_hAligment, int ai_vAligment, int ai_hGap, int ai_vGap)
	{
		super(ai_column, ai_row, ai_hAligment, ai_vAligment, ai_hGap, ai_vGap);
	}

}
