package Wallet




import scala.reflect.BeanProperty
import org.joda.time._
import org.hibernate.validator.constraints._
import org.apache.commons.lang3.time.DateFormatUtils
import java.util.Date

class Idcards {
		

	   
		@BeanProperty
		var cardid : String = _

	
    @BeanProperty
    @NotEmpty
    var name : String = _

	
  @BeanProperty
  @NotEmpty
  var number :String = _ 

  
   @BeanProperty
   var expiration_date : String = DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT.format(new Date())

}