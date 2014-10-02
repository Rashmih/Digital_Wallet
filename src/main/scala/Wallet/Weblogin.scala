package Wallet




import scala.reflect.BeanProperty
import org.hibernate.validator.constraints.NotEmpty



 class WebLogin {
 		
 		@BeanProperty
 		@NotEmpty
		var login_id : String = _

		@BeanProperty
		@NotEmpty
		var url : String = _

		@BeanProperty
		@NotEmpty
		var login : String = _

		@BeanProperty
		@NotEmpty
		var password : String = _

		

	


}