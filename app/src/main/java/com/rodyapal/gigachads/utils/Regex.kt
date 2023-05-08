package com.rodyapal.gigachads.utils

/**
 * 	This regular expression matches an email address that:
 * 	-	Starts with one or more characters that are not whitespace or @ symbol
	-	Followed by the @ symbol
	-	Followed by one or more characters that are not whitespace or @ symbol
	-	Followed by a dot (.) symbol
	-	Followed by two or more characters that are not whitespace or @ symbol
	-	Has a maximum length of 256 characters (including the @ and the domain name)
	-	Has a maximum length of 64 characters before the @ symbol
	-	The domain name has a minimum length of 2 characters and a maximum length of 63 characters
	-	The domain name ends with a valid top-level domain (TLD) extension that has a minimum length of 2 characters and a maximum length of 6 characters
 * */
const val EMAIL_REGEX_PATTERN = "(?=.{1,256})(?=.{1,64}@)[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}(?<=.{2,6})"

/**
 * 	This regular expression matches a password that:
	-	Contains at least one lowercase letter
	-	Contains at least one uppercase letter
	-	Contains at least one digit
	-	Contains at least one special character among @$!%*?&
	-	Has a minimum length of 8 characters
 * */
const val PASSWORD_REGEX_PATTERN = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}"