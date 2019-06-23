use cads1;
CREATE TABLE IF NOT EXISTS city (
  id INT NOT NULL,
  name VARCHAR(50) NOT NULL,
  primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS building(
   id int not null ,
   name VARCHAR(100),
   city VARCHAR(50),	
   country VARCHAR(50),
   province VARCHAR(50),
   pin VARCHAR(10),
   address text,
   floors int,   
   primary key(id)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  

CREATE TABLE IF NOT EXISTS flat(
  id int not null ,
  flatnumber varchar(50),
  buildingid int,
  area float,
  floor  varchar(10),  
  primary key(id),
  bedrooms int(10),
  parking varchar(20),
  foreign key (buildingid) references building(id)
  
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
  CREATE TABLE IF NOT EXISTS user (
  id int  NOT NULL,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50),
  email VARCHAR(50) NOT NULL,
  phone int(12),
  dateOfBirth VARCHAR(50),
  flatid int,
  password VARCHAR(50),
  primary key(id),
  foreign key fk_flatid(flatid) references flat(id)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
 
 CREATE TABLE IF NOT EXISTS ticket(
   id int NOT NULL ,
   description text,
   ticketType enum('maintenance','casual_expense','salary_deposit','check_balance','House_Work') not null,
   workerType enum('Plumber','Electrician','Cleaning','Carpenter'),
   state enum('NEW','ASSIGNED','IN_PROGRESS','CLOSED') NOT NULL,
   submitterid int,   
   ownerid int,
   note text,	
   dueDate VARCHAR(50),
   isrecurring boolean,
   primary key(id),
   foreign key (submitterid) references user(id) 
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
   create table if not exists visitor(
    id int not null ,
    name VARCHAR(50),
	phone VARCHAR(20),
	flatID int,
	purpose text,
	intime VARCHAR(30),
	outtime VARCHAR(30),
	parking int,
	primary key(id),    
    foreign key (flatID) references flat(id)        
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
   create table if not exists parking(
 	id int not null ,
    slot VARCHAR(20) ,
 	visitorId int,
 	primary key(id),
 	foreign key (visitorId) references visitor(id)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
 
  
 
