package ua.nure.kn156.danylenko;

import java.util.Calendar;
import java.util.Date;

public class User {
	private Long id;
	private String firstName;
	private String lastName;
	private Date date;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	

    public String getFullName() {
        if (lastName == null || firstName == null) {
            throw new IllegalArgumentException("Invalid format");
        }
        return new StringBuilder().append(lastName).append(", ").append(firstName).toString();
    }
    
    public long getAge() {
    	Calendar calendar=Calendar.getInstance();
    	long currentYear=calendar.get(Calendar.YEAR);
    	long currentDayOfYear=calendar.get(Calendar.DAY_OF_YEAR);
    	calendar.setTime(getDate());
    	long yearOfBirth=calendar.get(Calendar.YEAR);
    	long dayOfBirthOfYear=calendar.get(Calendar.DAY_OF_YEAR);
    	if (currentDayOfYear<dayOfBirthOfYear)
    	{
    		return currentYear-yearOfBirth-1;
    	}
    	
    	return currentYear-yearOfBirth ;
    	
    	
    }
	

}
