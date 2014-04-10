package com.ctech.entity;

import java.util.Date;

public class OrderItem {
	private Date time;
	private Number value;

	/**
	 * @param time
	 * @param value
	 */
	public OrderItem(Date time, Number value)
	{
		this.time = time;
		this.value = value;
	}

	/**
	 * @return the time.
	 */
	public Date getTime()
	{
		return time;
	}

	/**
	 * @return the value.
	 */
	public Number getValue()
	{
		return value;
	}
}
