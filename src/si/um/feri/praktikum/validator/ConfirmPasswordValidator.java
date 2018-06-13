package si.um.feri.praktikum.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("confirmPasswordValidator")
/**
 * 
 *ConfirmPasswordValidator implements Validator, with this class we 
 *validate users password input.
 *
 */
public class ConfirmPasswordValidator implements Validator {

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {

		UIInput passwordComponent = (UIInput) component.getAttributes().get("passwordComponent");
		String password = (String) passwordComponent.getValue();
		String confirmPassword = (String) value;

		if (confirmPassword != null && !confirmPassword.equals(password)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Confirm password is not the same as password", null));

		}
	}
}
