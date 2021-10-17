import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contacts {

    private Map<String, Contact> phonebook;
    private final String noContactError = "Такого контакта нет!";

    protected Contacts() {
        phonebook = new HashMap<>();
    }

    public void addContact(Contact contact) {
        String phone = contact.getPhone();
        phonebook.put(phone, contact);
    }

    public void removeContactByNameAndSurname(String name, String surname) {
        for (Contact c : phonebook.values()) {
            if (c.getName().equals(name) && c.getSurname().equals(surname)) {
                phonebook.remove(c.getPhone());
            } else System.out.println(noContactError);
        }
    }

    public void removeContact(Contact contact) {
        if (!phonebook.containsKey(contact.getPhone())) {
            phonebook.remove(contact.getPhone());
        } else System.out.println(noContactError);
    }

    public void searchContactByNumber(String phone) {
        if (!phonebook.containsKey(phone)) {
            System.out.println(noContactError);
        } else
            System.out.println("Результаты поиска:\nПо номеру телефона: " + phone + " найден контакт:\n" + phonebook.get(phone) + "\n");
    }

    public Contact returnContact(String name, String surname) {
        for (Contact c : phonebook.values()) {
            if (c.getName().equals(name) && c.getSurname().equals(surname)) {
                return c;
            }
        }
        System.out.println(noContactError);
        return null;
    }

    public void showAllContacts(Contacts contacts) {
        System.out.println("Ваша телефонная книга:");
        if (phonebook.isEmpty()) {
            System.out.println("Телефонная книга пуста!\n");
        } else for (Map.Entry<String, Contact> entry : phonebook.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println();
    }

    public List<MissedCallsItem> replaceContactsInList(MissedCalls missedCalls) {
        List<MissedCallsItem> missedCallsItemList = missedCalls.missedCallsToList();
        for (MissedCallsItem mci : missedCallsItemList) {
            String phone = mci.getPhone();
            for (Contact c : phonebook.values()) {
                if (c.getPhone().equals(phone)) {
                    phone = c.getNameAndSurname();
                    mci.setPhone(phone);
                }
            }
        }
        return missedCallsItemList;
    }
}