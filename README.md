#Project Requirements - Flight Reservation System

Objectives

To design, using good design principles with consideration for reuse and maintainability, simple software programmes to solve real-world problems
To implement a given design using Java
To exercise teamwork in different roles of a software development team

The Context

To expand its route offering to north-western India, Singapore Airlines (www.singaporeair.com) and its regional wing, SilkAir (www.silkair.com), are partnering SpiceJet (www.spicejet.com).  Under the tie-up, travellers from cities like Delhi, Mumbai and Pune will be able to enjoy a seamless flight connection to Singapore.  

SpiceJet will provide the connection from these north-western cities to Bangalore, Chennai, Hyderabad and Kolkata, where SilkAir currently operates flights to Singapore.

For example, on Nov1 (Wed), a traveller can take SpiceJet flight SG109 from Delhi (DEL) to Chennai (MAA), and then Singapore Airlines flight SQ529 from Chennai (MAA) to Singapore (SIN).  

 SG109	 1800 hrs
 2050 hrs
 	 DEL	 MAA
 SQ529	 2315 hrs
 0555 hrs (+1 day)
 	 MAA	 SIN

Transit time = 2hrs25mins
Total travel time = 9hrs25mins

The Requirements

Develop a Flight Reservation System for travellers from Delhi, Mumbai and Pune to
search for one-way flights to Singapore
view the list of connecting options, ranked by flight duration
select and book flights for up to 10 people in a group.
Flight details are provided in the following *.csv files: 

2016.spicejet.csv
2016.silkair.csv


The Assumptions

There needs to be a minimum of 120 minutes between connecting flights for baggage transfer, and a maximum of 6 hours.

Travel will take place between October 1, 2016 and November13, 2016 (both dates inclusive). 

There is a time difference of 2-1/2 hours between India and Singapore.

Assume that each flight can take a total of 15 passengers.

Each booking (for up to 10 persons in a group) requires ONE name, and is uniquely identified by a Flight Booking Number.  All bookings are to be persisted.
 
 
 
Thanks To Prof. Ee. Kian Wong for her help during the project.
