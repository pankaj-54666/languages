
General Flow: userSearch Match for particular day -> user select seat for that match -> option to select Snacks ->  User do payment for their choice -> user get notified about payment & Ticker

1. Requirement:
Book Ticket For Match
Multiple Match at different time
Option to buy snacks 
User Need to see all the match available for particular day
Single Stadium or list of stadium on different state??


Components:
web-servver
Application server
Ticketing microservice
Food Order Micorservice
Payment microservice
Notification Microserver - send user ticket details via email,msg etc
SQL vs NoSQL -> 

Main Database Table/Collection:
userTable (NoSQL)
{
	Name:
	Location:
	userID:
	….
}

Movie Database(SQL)
StadiumTable: stadiumID, location

-> MatchTable (matchID, MatchInfo , Teams , Timing, seatingTable_FK , 
-> SeatingArragmentTable: (seatID, AvailableSeats, seatStatus, userID_FK, matchTable_FK)
-> 
	
© Food Database:
{
	menu_id
}

FoodMenu 
{
	iteamID, 
	foodName
	…
}


Data Sharding: 

Load Balancer:


Client → web-server →  Application server → Booking server..







