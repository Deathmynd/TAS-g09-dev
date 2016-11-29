/*******************************************************************************
 * Copyright (c) 2014-2015 University of Luxembourg.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Alfredo Capozucca - initial API and implementation
 *     Christophe Kamphaus - Remote implementation of Actors
 *     Thomas Mortimer - Updated client to MVC and added new design patterns
 ******************************************************************************/
package lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib;

import java.io.Serializable;

/**
 * The Class PtInteger to wrap the primitive type of integer .
 */
public class PtInteger implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;
	
	/** The value to be wrapped. */
	int value;

	/**
	 * Instantiates a new primitive type of integer.
	 *
	 * @param v the value to be wrapped
	 */
	public PtInteger(int v){
		value = v;
	}
	
	/**
	 * Returns the value from inside the wrapper.
	 *
	 * @return The value of the boolean
	 */
	public int getValue(){
		return value;
	}
	
	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof PtInteger))
			return false;
		PtInteger aInteger = (PtInteger)obj;
		if (aInteger.value != this.value )
			return false;
		return true;
	}
	
	@Override
	public int hashCode(){
	    return value;
	}

}
