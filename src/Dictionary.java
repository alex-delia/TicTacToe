//Alex D'Elia
//251021780
//adelia3
//Oct. 17/19

import java.util.*;

public class Dictionary implements DictionaryADT {
	
	//instance variables
	private ArrayList<LinkedList<Record>> hashtable;
	private LinkedList<Record> list;
	private int size;
	private int numRecords = 0;
	
	//hash function to get the value of each key
	private int hash(String config, int tableSize) {
		int keyVal = 0;
		
		for(int i = 0; i < config.length(); i++) {
			keyVal += (keyVal * 59 + (int)config.charAt(i)) % tableSize;
			}
	
		return keyVal % tableSize;
	}
	
	//inserts a Record pair into the dictionary
	public int insert (Record pair) throws DictionaryException{
		//gets hashvalue for given pair
		int hashVal = hash(pair.getConfig(), size);
		
		//checks to see if that config already exists in the dictionary, and if it does throws a dictionary exception
		if(get(pair.getConfig()) != -1) {
			throw new DictionaryException();
			
		//else, if nothing exists at the hashvalue, create a new linked list, add it to the hashtable, and add the Record
		//to that linked list
		}else{
			if(hashtable.get(hashVal) == null) {
				// doesnt exist, new one
				list = new LinkedList<Record>();
				hashtable.set(hashVal, list);
				list.addFirst(pair);
				numRecords++;
				return 0;
				
			//Otherwise, if a linked list already exists at this hashvalue, add the pair to the end of the list
			} else {
				// exsits, lets add to the linekdlist 
				list = hashtable.get(hashVal);
				list.add(pair);
				numRecords++;
				return 1;
			}
			
		}
			
	}
	
	//removes an element from the hashtable if it exists
	public void remove (String config) throws DictionaryException{
		//gets hashvalue for given config
		int hashVal = hash(config, size);
		
		//if a linkedlist exists at this hash value, retrieves that list from the table and puts it into a temp list
		if(hashtable.get(hashVal) != null) { 
			list = hashtable.get(hashVal);
		
			//iterates through the linked list and if a config in thr list matches the given config, that item is removed
			//from the list
			for(int i=0; i< list.size(); i++) {
				Record element = list.get(i);
				if(element.getConfig().equals(config)) {
					list.remove(element);
					numRecords--;
					}
				}
			//if that record does not exist a dictionary exception is thrown
			}else {
				throw new DictionaryException();
			}
		}
	

	//gets the score stored in the hashtable for a given config
	public int get (String config) {
		//gets hashvalue for given config
		int hashVal = hash(config, size);
	
		//if a linkedlist exists at this hash value, retrieves that list from the table and puts it into a temp list
		if(hashtable.get(hashVal) != null) { 
			list = hashtable.get(hashVal);
			
			//iterates through the linked list and if a config in the list matches the given config, that item's score
			//is returned in the function
			for(int i=0; i < list.size(); i++) {			
				Record element = list.get(i);
				if(element.getConfig().equals(config)) {
					 return element.getScore();
				}
			}
		}
		//if the item does not exist -1 is returned
		return -1;
	}
//	
	//returns the number of elements being stored in the hashtable
	public int numElements() {
		 return numRecords;
	}

	//dictionary constructor creating our hashtable and filling it with null spaces
	public Dictionary(int dictSize) {
		size = dictSize;;
		hashtable = new ArrayList<LinkedList<Record>>(size);
		for(int i = 0; i < size; i++) {
			hashtable.add(null);
		}
		numRecords = 0;
		
	}
}
