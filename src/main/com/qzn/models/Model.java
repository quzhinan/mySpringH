package com.qzn.models;

import java.io.Serializable;

public abstract class Model<ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = 3973318060557338738L;

	public abstract ID getId();
}
