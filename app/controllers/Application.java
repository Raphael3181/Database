package controllers;

import play.*;
import play.mvc.*;
import play.data.*;

import views.html.*;
import models.*;

public class Application extends Controller {
	static Form<Contact> ContactForm = Form.form(Contact.class);

	public static Result index() {
		return redirect(routes.Application.contacts());
	}

	public static Result contacts() {
		return ok(views.html.index.render(Contact.all(), ContactForm));
	}

	public static Result newContact() {
		Form<Contact> filledForm = ContactForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(views.html.index
					.render(Contact.all(), filledForm));
		} else {
			Contact.create(filledForm.get());
			return redirect(routes.Application.contacts());
		}
	}

	public static Result editContact() {
		Form<Contact> filledForm = ContactForm.bindFromRequest();
		if (filledForm.hasErrors()) {
			return badRequest(edit.render(filledForm.get(), filledForm));
		} else {
			Contact.update(filledForm.get());
			return redirect(routes.Application.contacts());
		}
	}

	public static Result openContact(Long id) {
		return ok(edit.render(Contact.getById(id), ContactForm));
	}

	public static Result deleteContact(Long id) {
		Contact.delete(id);
		return redirect(routes.Application.contacts());
	}

}