package lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.secondary;

import java.io.Serializable;

import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.design.JIntIs;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.system.types.primary.DtPhoneNumber;
import lu.uni.lassy.excalibur.examples.icrash.dev.java.types.stdlib.PtBoolean;

public class DtCachedSms  implements Serializable, JIntIs {
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 227L;

	public DtSMS sms = null;
	
	public DtPhoneNumber phone = null;
	
	public DtCachedSms(DtPhoneNumber aPhone, DtSMS aSms) {
		sms = aSms;
		phone = aPhone;
	}
	@Override
	public PtBoolean is() {
		if (sms != null && phone != null)
			return new PtBoolean(true);
		return new PtBoolean(false);
	}

}
