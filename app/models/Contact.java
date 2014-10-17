package models;

import java.util.*;
import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Contact extends Model {

	@Id
	public Long id;

	@Required
	public String name;
	public String homephone;
	public String mobilephone;

	public static Finder<Long, Contact> find = new Finder(Long.class, Contact.class);

	public static List<Contact> all() {
		return find.all();
	}

	public static void create(Contact Contact) {
		Contact.save();
	}
	public static Contact getById(Long Id) {
		return find.byId(Id);
	}
	public static void delete(Long id) {
		find.ref(id).delete();
	}
	public static void update(Contact contact) {
		contact.update();
	}
}