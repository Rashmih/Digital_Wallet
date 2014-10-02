package Wallet



import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.{RequestBody,ResponseBody,ResponseStatus}
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import scala.collection.mutable._
import java.util.ArrayList
import javax.validation.Valid
import scala.collection.JavaConversions._
import scala.collection.mutable.HashMap
import scala.collection.mutable.Set
import scala.collection.mutable.MultiMap
import scala.collection.mutable.ListBuffer
import java.util.Date
import org.apache.commons.lang3.time.DateFormatUtils





@Configuration
@EnableAutoConfiguration
@ComponentScan
@RestController
@RequestMapping(value = Array("/api/v1/"))
class UserController {


val UserMap = Map[String,User]()

val cards : ArrayList[Idcards]= new ArrayList[Idcards]()
val CardMap = Map[String,ArrayList[Idcards]]()

val logins : ArrayList[WebLogin]= new ArrayList[WebLogin]()
val WebMap = Map[String,ArrayList[WebLogin]]()

val acc : ArrayList[BankAcc]= new ArrayList[BankAcc]()
val AccMap = Map[String,ArrayList[BankAcc]]()

//val counter = new AtomicInterger()
var id = 65983

var c_id = 1234567

var l_id = 659835
var b_id = 498657


@RequestMapping(value = Array ("users"),method = Array (RequestMethod.POST), consumes = Array("application/json"), produces = Array("application/json"))
@ResponseStatus(value = HttpStatus.CREATED)
@ResponseBody
def create(@RequestBody @Valid user: User): User =  {

var newuser : User = new User()
	//User.userid = counter.incrementAndGet()
	//val timestamp : DateTime = new DateTime()
	newuser.email_id= user.email_id
	newuser.password=user.password
	newuser.name = user.name
	newuser.userid="u-"+id
	newuser.created_at=user.created_at
	
	UserMap += (newuser.userid -> newuser)

	id +=1
  	return newuser

  	  }

@RequestMapping(value= Array ("users/{userid}"),method =Array (RequestMethod.GET),produces = Array ("application/json"))
@ResponseStatus(value = HttpStatus.OK)
@ResponseBody
def viewuser(@PathVariable("userid") @Valid userid : String) : User = {
	
	if(UserMap.contains(userid))
	{
		return UserMap(userid)
	}
	else return null 

}

@RequestMapping(value=Array("users/{userid}"),method = Array(RequestMethod.PUT),consumes = Array("application/json"), produces = Array("application/json"))
@ResponseStatus(value = HttpStatus.CREATED)
@ResponseBody
def updateuser(@PathVariable("userid") @Valid userid :String ,@RequestBody user : User) :User = {
	

	
	if (UserMap.contains(userid))
	{
	var newuser : User = new User()
	newuser.email_id=user.email_id
	newuser.password=user.password
	newuser.name = user.name
	newuser.userid=userid
	var update_at : String = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(new Date())
	
	newuser.updated_at=update_at
	UserMap += (newuser.userid -> newuser)
	return newuser
}
else return null 
}

// IDCARDS
@RequestMapping(value = Array("users/{userid}/idcards"),method = Array(RequestMethod.POST), consumes = Array("application/json"), produces = Array("application/json"))
@ResponseStatus(value = HttpStatus.CREATED)
@ResponseBody
def createidcards(@PathVariable ("userid") @Valid userid :String,@RequestBody @Valid idcards :Idcards): Idcards ={

 if ( UserMap.contains(userid))
 {


	var newcard = new Idcards()
	newcard.name=idcards.name
	newcard.number=idcards.number
	newcard.cardid="c-" +c_id
	newcard.expiration_date=idcards.expiration_date

	cards += newcard


	CardMap += (userid -> cards)
	
	
	 c_id +=1
	 return newcard

	}
	else return null
}


	 
@RequestMapping(value= Array ("users/{userid}/idcards"),method =Array (RequestMethod.GET),produces = Array ("application/json"))
@ResponseStatus(value = HttpStatus.OK)
@ResponseBody
def Listallcards(@PathVariable ("userid") @Valid userid :String) : ArrayList[Idcards] =  {
	
	if (CardMap.contains(userid))
	{
		return CardMap(userid)
	}
	else return null

}

@RequestMapping(value= Array ("users/{userid}/idcards/{cardid}"),method =Array (RequestMethod.DELETE),produces = Array ("application/json"))
@ResponseStatus(value = HttpStatus.NO_CONTENT)
@ResponseBody
def Deleteidcard(@PathVariable ("userid") @Valid userid :String,@PathVariable ("cardid") @Valid cardid :String) :String =  {
	if (CardMap.contains(userid))
	{
		
		for (idcard : Idcards <- CardMap(userid)	
			if idcard.cardid.equals(cardid))
			CardMap(userid).remove(idcard)
			return "Deleted"
	}
return "Not found"
}

//WEB_LOGIN


@RequestMapping(value = Array("users/{userid}/Weblogins"),method = Array(RequestMethod.POST), consumes = Array("application/json"), produces = Array("application/json"))
@ResponseStatus(value = HttpStatus.CREATED)
@ResponseBody
def createlogin(@PathVariable ("userid") @Valid userid :String,@RequestBody @Valid weblogin :WebLogin) : WebLogin = {

 if ( UserMap.contains(userid))
 {


	var temp = new WebLogin()
	temp.url=weblogin.url
	temp.login=weblogin.login
	temp.password=weblogin.password
	temp.login_id="l-" +l_id

	 logins += temp

	WebMap += (userid -> logins)
	
	
	 l_id +=1
	 return temp

	}
	else return null
}


	 
@RequestMapping(value= Array ("user/{userid}/Weblogins"),method =Array (RequestMethod.GET),produces = Array ("application/json"))
@ResponseStatus(value = HttpStatus.OK)
@ResponseBody
def Listlogins(@PathVariable ("userid") @Valid userid :String) : ArrayList[WebLogin] =  {
	
	if (WebMap.contains(userid))
	{
		return WebMap(userid)
	}
	else return null

}

@RequestMapping(value= Array ("user/{userid}/Weblogins/{login}"),method =Array (RequestMethod.DELETE),produces = Array ("application/json"))
@ResponseStatus(value = HttpStatus.NO_CONTENT)
@ResponseBody
def Deletelogin(@PathVariable ("userid") @Valid userid :String,@PathVariable ("login") @Valid login :String) :String =  {
	if (WebMap.contains(userid))
	{
		
		for (web_login : WebLogin <- WebMap(userid)	
			if web_login.login.equals(login))
			WebMap(userid).remove(login)
			return "Deleted"
	}
return "Not found"
}

//BANK ACCOUNT

@RequestMapping(value = Array("users/{userid}/bankaccounts"),method = Array(RequestMethod.POST), consumes = Array("application/json"), produces = Array("application/json"))
@ResponseBody
def createacc(@PathVariable ("userid") @Valid userid :String,@RequestBody @Valid bankacc :BankAcc) : BankAcc = {

 if ( UserMap.contains(userid))
 {


	var temp = new BankAcc()
	temp.account_name=bankacc.account_name
	temp.routing_number=bankacc.routing_number
	temp.account_number=bankacc.account_number
	temp.ba_id="b-" +b_id

	 acc += temp

	AccMap += (userid -> acc)
	
	
	 b_id +=1
	 return temp

	}
	else return null
}


	 
@RequestMapping(value= Array ("user/{userid}/bankaccounts"),method =Array (RequestMethod.GET),produces = Array ("application/json"))
@ResponseBody
def ListAcc(@PathVariable ("userid") @Valid userid :String) : ArrayList[BankAcc] =  {
	
	if (AccMap.contains(userid))
	{
		return AccMap(userid)
	}
	else return null

}

@RequestMapping(value= Array ("user/{userid}/bankaccounts/{ba_id}"),method =Array (RequestMethod.DELETE),produces = Array ("application/json"))
@ResponseStatus(value = HttpStatus.NO_CONTENT)
@ResponseBody
def DeleteAcc(@PathVariable ("userid") @Valid userid :String,@PathVariable ("ba_id") @Valid ba_id :String) : String =  {
	if (AccMap.contains(userid))
	{
		
		for (bank_acc : BankAcc <- AccMap(userid)	
			if bank_acc.ba_id.equals(ba_id))
			AccMap(userid).remove(ba_id)
			return "Deleted"
	}
return "Not found"
}
}


