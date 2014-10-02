package Wallet




import scala.reflect.BeanProperty
import org.joda.time._
import org.hibernate.validator.constraints.NotEmpty
import org.apache.commons.lang3.time.DateFormatUtils


class BankAcc {
		
	    
	@BeanProperty
	@NotEmpty
	var ba_id : String = _

	
	@BeanProperty
    var account_name : String = _

	
  @BeanProperty
  @NotEmpty
  var routing_number :String = _ 

  @BeanProperty
  @NotEmpty
  var account_number :String = _


}