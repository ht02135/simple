package simple.packages.datetime;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.time.*;
import java.time.temporal.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.stream.*;

import simple.packages.*;
import simple.packages.datetime.*;
import simple.packages.stream.*;

import java.sql.*;

public class DateConvertUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	/**
	 * Calls {@link #asLocalDate(Date, ZoneId)} with the system default time zone.
	 */
	public static LocalDate asLocalDate(java.util.Date date) {
		return asLocalDate(date, ZoneId.systemDefault());
	}

	/**
	 * Creates {@link LocalDate} from {@code java.util.Date} or it's subclasses.
	 * Null-safe.
	 */
	public static LocalDate asLocalDate(java.util.Date date, ZoneId zone) {
		if (date == null)
			return null;

		if (date instanceof java.sql.Date)
			return ((java.sql.Date) date).toLocalDate();
		else
			return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDate();
	}

	/**
	 * Calls {@link #asLocalDateTime(Date, ZoneId)} with the system default time
	 * zone.
	 */
	public static LocalDateTime asLocalDateTime(java.util.Date date) {
		return asLocalDateTime(date, ZoneId.systemDefault());
	}

	/**
	 * Creates {@link LocalDateTime} from {@code java.util.Date} or it's subclasses.
	 * Null-safe.
	 */
	public static LocalDateTime asLocalDateTime(java.util.Date date, ZoneId zone) {
		if (date == null)
			return null;

		if (date instanceof java.sql.Timestamp)
			return ((java.sql.Timestamp) date).toLocalDateTime();
		else
			return Instant.ofEpochMilli(date.getTime()).atZone(zone).toLocalDateTime();
	}

	/**
	 * Calls {@link #asUtilDate(Object, ZoneId)} with the system default time zone.
	 */
	public static java.util.Date asUtilDate(Object date) {
		return asUtilDate(date, ZoneId.systemDefault());
	}

	/**
	 * Creates a {@link java.util.Date} from various date objects. Is null-safe.
	 * Currently supports:
	 * <ul>
	 * <li>{@link java.util.Date}
	 * <li>{@link java.sql.Date}
	 * <li>{@link java.sql.Timestamp}
	 * <li>{@link java.time.LocalDate}
	 * <li>{@link java.time.LocalDateTime}
	 * <li>{@link java.time.ZonedDateTime}
	 * <li>{@link java.time.Instant}
	 * </ul>
	 * 
	 * @param zone Time zone, used only if the input object is LocalDate or
	 *             LocalDateTime.
	 * 
	 * @return {@link java.util.Date} (exactly this class, not a subclass, such as
	 *         java.sql.Date)
	 */
	public static java.util.Date asUtilDate(Object date, ZoneId zone) {
		if (date == null)
			return null;

		if (date instanceof java.sql.Date || date instanceof java.sql.Timestamp)
			return new java.util.Date(((java.util.Date) date).getTime());
		if (date instanceof java.util.Date)
			return (java.util.Date) date;
		if (date instanceof LocalDate)
			return java.util.Date.from(((LocalDate) date).atStartOfDay(zone).toInstant());
		if (date instanceof LocalDateTime)
			return java.util.Date.from(((LocalDateTime) date).atZone(zone).toInstant());
		if (date instanceof ZonedDateTime)
			return java.util.Date.from(((ZonedDateTime) date).toInstant());
		if (date instanceof Instant)
			return java.util.Date.from((Instant) date);

		throw new UnsupportedOperationException(
				"Don't know hot to convert " + date.getClass().getName() + " to java.util.Date");
	}

	/**
	 * Creates an {@link Instant} from {@code java.util.Date} or it's subclasses.
	 * Null-safe.
	 */
	public static Instant asInstant(Date date) {
		if (date == null)
			return null;
		else
			return Instant.ofEpochMilli(date.getTime());
	}

	/**
	 * Calls {@link #asZonedDateTime(Date, ZoneId)} with the system default time
	 * zone.
	 */
	public static ZonedDateTime asZonedDateTime(Date date) {
		return asZonedDateTime(date, ZoneId.systemDefault());
	}

	/**
	 * Creates {@link ZonedDateTime} from {@code java.util.Date} or it's subclasses.
	 * Null-safe.
	 */
	public static ZonedDateTime asZonedDateTime(Date date, ZoneId zone) {
		if (date == null)
			return null;
		else
			return asInstant(date).atZone(zone);
	}

}
