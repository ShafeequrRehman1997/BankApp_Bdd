package org.cap.util;

public class AccountUtil {
private static int accountNo=0;
public static int generateAccountNo() {
	return ++accountNo;
	
}
}
