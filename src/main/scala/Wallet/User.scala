package Wallet




import scala.reflect.BeanProperty
import org.hibernate.validator.constraints.NotEmpty
import org.hibernate.validator.constraints.Email
import java.util.Date
import org.apache.commons.lang3.time.DateFormatUtils



 class User {

 	
		
	@BeanProperty
	var userid : String = _

	
	@BeanProperty
	@NotEmpty
	@Email
    var email_id : String = _

	
   @BeanProperty
   @NotEmpty
   var password :String = _ 

  @BeanProperty
  var name : String = _

  @BeanProperty
  var created_at: String = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(new Date())


 @BeanProperty
 var updated_at : String = _

}