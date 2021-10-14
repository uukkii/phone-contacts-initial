import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Contacts {

    protected static Map<String, Contact> phonebook;

    public Contacts() {
        phonebook = new HashMap<>();
    }

    public static void addContact(Contact contact) {
        String phone = contact.getPhone();
        phonebook.put(phone, contact);
    }

    public static void removeContactByNameAndSurname(String name, String surname) {
        for (Contact c : phonebook.values()) {
            if (c.getName().equals(name) && c.getSurname().equals(surname)) {
                phonebook.remove(c.getPhone());
            } else System.out.println("Такого контакта нет!");
        }
    }

    public static void searchContactByNumber(String phone) {
        if (!phonebook.containsKey(phone)) {
            System.out.println("Такого контакта в книге нет!\n");
        } else
            System.out.println("Результаты поиска:\nПо номеру телефона: " + phone + " найден контакт:\n" + phonebook.get(phone) + "\n");
    }

    public static Contact returnContact(String name, String surname) {
        for (Contact c : phonebook.values()) {
            if (c.getName().equals(name) && c.getSurname().equals(surname)) {
                return c;
            }
        }
        System.out.println("Такого контакта нет!");
        return null;
    }

    public static void rewriteContact() {
    }

    public static void showAllContacts() {
        System.out.println("Ваша телефонная книга:");
        for (Map.Entry<String, Contact> entry : phonebook.entrySet()) {
            System.out.println(entry.getValue());
        }
        System.out.println();
    }

    public static List<MissedCallsItem> replaceContactsInList() {
        List<MissedCallsItem> missedCallsItemList = MissedCalls.missedCallsToList();
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