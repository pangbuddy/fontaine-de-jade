package be.fontainedejade.controller;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.TimeZone;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class LanguageBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_BUNDLE = "com.bitongline.lgs.resource.msg";

	private Locale locale;
	private String language;
	private TimeZone timeZone;
	private String localeDatePattern;
	private transient ResourceBundle resourceBundle;

	public LanguageBean() {
		super();
		FacesContext ctx = FacesContext.getCurrentInstance();
		this.locale = ctx == null ? Locale.ENGLISH : ctx.getViewRoot().getLocale();
		this.language = this.locale.getLanguage();
		this.timeZone = Calendar.getInstance(this.locale).getTimeZone();
		SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
		this.localeDatePattern = sdf.toLocalizedPattern();
		this.resourceBundle = ResourceBundle.getBundle(ctx == null ? DEFAULT_BUNDLE : ctx.getApplication().getMessageBundle(), this.locale, Thread.currentThread().getContextClassLoader());
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
		this.locale = new Locale(language);
		this.timeZone = Calendar.getInstance(this.locale).getTimeZone();
		SimpleDateFormat sdf = (SimpleDateFormat) DateFormat.getTimeInstance(DateFormat.SHORT, locale);
		this.localeDatePattern = sdf.toLocalizedPattern();
		this.resourceBundle = ResourceBundle.getBundle(FacesContext.getCurrentInstance().getApplication().getMessageBundle(), this.locale, Thread.currentThread().getContextClassLoader());
		FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
	}

	public String getLocalMessage(String label) {
		String message = "";
		try {
			message = this.resourceBundle.getString(label);
		} catch (MissingResourceException e) {
			message = "Error Message!";
		}
		return message;
	}

	public ResourceBundle getResourceBundle() {
		return resourceBundle;
	}

	public Locale getLocale() {
		return locale;
	}

	public TimeZone getTimeZone() {
		return timeZone;
	}

	/**
	 * @return the localeDatePattern
	 */
	public String getLocaleDatePattern() {
		return localeDatePattern;
	}
}
