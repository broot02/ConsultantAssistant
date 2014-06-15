package root.consultantassistant.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <code>ConsultantList</code> is used for the purpose of validating that a
 * consultant is a valid user.
 * 
 * @author Brad Root
 *
 */
public class ConsultantList {

	private List<Consultant> users;

	/**
	 * 
	 */
	public ConsultantList() {
		users = new ArrayList<Consultant>();
	}

	/**
	 * Copy constructor, expects an <code>ArrayList</code> of the
	 * <code>Consultant</code> type, and passes them to a new object.
	 * 
	 * @param consultants
	 *            - An <code>ArrayList</code> of the valid
	 *            <code>Consultant</code> users.
	 */
	public ConsultantList(ArrayList<Consultant> consultants) {
		users = new ArrayList<Consultant>(consultants);
	}

	/**
	 * Adds a new <code>Consultant</code> to the list of users, only if there is
	 * not a user with the same info already in the user list.
	 * 
	 * @param consultant
	 *            - <code>Consultant</code> to be added to the list of valid
	 *            users.
	 * @return <code>true</code> if the consultant is not already in the list,
	 *         <code>false</code> otherwise.
	 */
	public boolean addConsultant(Consultant consultant) {
		if (!users.contains(consultant)) {
			users.add(consultant);
			return true;
		}
		return false;
	}

	/**
	 * Removes the <code>Consultant</code> from the list of valid users, returns
	 * a boolean value which depends on the fact of whether or not the user is
	 * in the list.
	 * 
	 * @param consultant
	 *            - <code>Consultant</code> to be removed from the list of valid
	 *            users.
	 * @return <code>true</code> if the consultant is in the users list, <code>false</code> otherwise.
	 */
	public boolean removeConsultant(Consultant consultant) {
		return users.remove(consultant);
	}

	/**
	 * @param consultant
	 * @return
	 */
	public boolean isConsultant(Consultant consultant) {
		return users.contains(consultant);
	}

	/**
	 * Validates the consultant as a valid user, based on the userId and the
	 * password. Will return null, if invalid.
	 * 
	 * @param userId
	 *            - Input from the User, it is expected to be a valid userId of
	 *            a consultant.
	 * @param password
	 *            - Input from the User, it is expected to be a valid password
	 *            in conjunction with the valid userId.
	 * @return The validated consultant will be returned, a valid userId and
	 *         password. Otherwise null is returned, ensure that a null check is
	 *         performed before manipulating the return value of this method.
	 */
	public Consultant validateConsultant(String userId, String password) {
		for (Consultant consultant : users) {
			if (consultant.getUserId().equals(userId)
					&& consultant.getPassword().equals(password)) {
				return consultant;
			}
		}
		// If the userId/password is not validated, null is returned. Ensure
		// there is a null check before processing
		// return result of this method.
		return null;
	}

}
