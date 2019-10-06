<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Farmer's Market Search Engine</title>
</head>
<body>
<div style="text-align:center">
Welcome to Bohan Wu's Farmer's Market Search Engine!
</div>

<br>
<br>

<form action="result" method="post">
	Search within 
	<select name="dis">
		<option value="30">30 miles</option>
		<option value="50">50 miles</option>
		<option value="100">100 miles</option>
		<option value="200">200 miles</option>
		<option value="500">500 miles</option>
	</select>
	<br>
	<br>
	<br>
	
	1.<br>
	Enter ZIP Code: <input type="text" name="zip"><br>
	
	<input type="submit"><br>
	<br>
	<br>
	
	OR
	
	<br>
	<br>
	2.<br>
	Enter city and state: <input type = "text" name="city">
	&nbsp;
	<select name="state">
		<option value="">Select</option>
		<option value="Alabama">Alabama</option>
		<option value="Alaska">Alaska</option>
		<option value="American Samoa">American Samoa</option>
		<option value="Arizona">Arizona</option>
		<option value="Arkansas">Arkansas</option>
		<option value="California">California</option>
		<option value="Colorado">Colorado</option>
		<option value="Connecticut">Connecticut</option>
		<option value="Delaware">Delaware</option>
		<option value="District of Columbia">District of Columbia</option>
		<option value="Florida">Florida</option>
		<option value="Georgia">Georgia</option>
		<option value="Guam">Guam</option>
		<option value="Hawaii">Hawaii</option>
		<option value="Idaho">Idaho</option>
		<option value="Illinois">Illinois</option>
		<option value="Indiana">Indiana</option>
		<option value="Iowa">Iowa</option>
		<option value="Kansas">Kansas</option>
		<option value="Kentucky">Kentucky</option>
		<option value="Louisiana">Louisiana</option>
		<option value="Maine">Maine</option>
		<option value="Maryland">Maryland</option>
		<option value="Massachusetts">Massachusetts</option>
		<option value="Michigan">Michigan</option>
		<option value="Minnesota">Minnesota</option>
		<option value="Mississippi">Mississippi</option>
		<option value="Missouri">Missouri</option>
		<option value="Montana">Montana</option>
		<option value="Nebraska">Nebraska</option>
		<option value="Nevada">Nevada</option>
		<option value="New Hampshire">New Hampshire</option>
		<option value="New Jersey">New Jersey</option>
		<option value="New Mexico">New Mexico</option>
		<option value="New York">New York</option>
		<option value="North Carolina">North Carolina</option>
		<option value="North Dakota">North Dakota</option>
		<option value="Northern Mariana Islands">Northern Mariana Islands</option>
		<option value="Ohio">Ohio</option>
		<option value="Oklahoma">Oklahoma</option>
		<option value="Oregon">Oregon</option>
		<option value="Pennsylvania">Pennsylvania</option>
		<option value="Puerto Rico">Puerto Rico</option>
		<option value="Rhode Island">Rhode Island</option>
		<option value="South Carolina">South Carolina</option>
		<option value="South Dakota">South Dakota</option>
		<option value="Tennessee">Tennessee</option>
		<option value="Texas">Texas</option>
		<option value="Utah">Utah</option>
		<option value="U.S. Virgin Islands">U.S. Virgin Islands</option>
		<option value="Vermont">Vermont</option>
		<option value="Virginia">Virginia</option>
		<option value="Washington">Washington</option>
		<option value="West Virginia">West Virginia</option>
		<option value="Wisconsin">Wisconsin</option>
		<option value="Wyoming">Wyoming</option>		
	</select>
	<br>



	<input type="submit">


</form>



</body>
</html>