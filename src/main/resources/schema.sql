use cads1;
CREATE TABLE IF NOT EXISTS building(
   id int not null AUTO_INCREMENT,
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
  id int not null AUTO_INCREMENT,
  flatnumber varchar(50),
  buildingid int,
  area float,
  floor  varchar(10),  
  primary key(id),
  bedrooms int(10),
  parking varchar(20),
  cankidsgoout boolean,
  foreign key (buildingid) references building(id)  
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
  CREATE TABLE IF NOT EXISTS user (
  id int  NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(50) NOT NULL,
  lastname VARCHAR(50),
  middlename VARCHAR(50),
  email VARCHAR(50) NOT NULL,
  owner VARCHAR(50) NOT NULL,
  phone int(12),
  ownerphone int(12), 
  dateofbirth VARCHAR(50),
  usergroup VARCHAR(50),
  flatid int,
  buildingid int,
  password VARCHAR(50),
  salt VARCHAR(50),
  token VARCHAR(50),
  primary key(id), 
  foreign key (buildingid) references building(id),
  foreign key (flatid) references flat(id)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE IF NOT EXISTS workers (
  id int  NOT NULL AUTO_INCREMENT,
  name VARCHAR(50) NOT NULL,
  usergroup VARCHAR(50),
  phonenumber int(12),  
  address text,  
  type enum('Plumber','Electrician','Cleaning','Carpenter','user'),  
  agentid int,
  feedback text,
  rating VARCHAR(50), 
  primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 CREATE TABLE IF NOT EXISTS ticket(
   id int  NOT NULL AUTO_INCREMENT,
   description text,
   tickettype enum('maintenance','casual_expense','salary_deposit','check_balance','House_Work') not null,
   workertype enum('Plumber','Electrician','Cleaning','Carpenter','user'),
   state enum('NEW','ASSIGNED','IN_PROGRESS','CLOSED') ,
   submitterid int,   
   ownerid int,
   note text,	
   duedate VARCHAR(50),
   isrecurring boolean,
   primary key(id)   
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
   create table if not exists visitor(
    id int not null AUTO_INCREMENT,
    name VARCHAR(50),
	phone VARCHAR(20),
	flatid int,
    buildingid int,
	purpose text,
	intime VARCHAR(30),
	outtime VARCHAR(30),
	parking int,
	primary key(id),    
    foreign key (flatID) references flat(id)        
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
    
   create table if not exists parking(
 	id int not null AUTO_INCREMENT,
    slot VARCHAR(20) ,
 	visitorid int,
 	primary key(id)   
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 create table if not exists ticketlog( 
 id int not null auto_increment,
 ticketid int not null,
 previousid int ,
 currentid int,
 previousstate enum('NEW','ASSIGNED','IN_PROGRESS','CLOSED') ,
 currentstate enum('NEW','ASSIGNED','IN_PROGRESS','CLOSED') ,
 comment text,
 primary key(id),
 foreign key(ticketid) references ticket(id)
 )ENGINE=InnoDB DEFAULT CHARSET=utf8;
 
 create table if not exists signup(
    id int not null AUTO_INCREMENT,
    firstname VARCHAR(50) NOT NULL,
    lastname VARCHAR(50),
    email VARCHAR(50) NOT NULL,
    buildingname VARCHAR(100),
    buildingcity VARCHAR(50),
    buildingcountry VARCHAR(50),
    buildingpin VARCHAR(10),
    buildingaddress text,
    singupid varchar(255) not null,
    signuptm datetime not null,
    signupstate int not null,
    PRIMARY KEY(id),
    UNIQUE(singupid)
 ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
  
 
