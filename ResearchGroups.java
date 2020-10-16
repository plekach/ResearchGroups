/**
 * Lab 2: Debugging with an IDE and Prefix Tree) <br />
 * The {@code ResearchGroup} class uses a 2D array to store the names of group members
 * Author: Paige Lekach
 */
import java.util.Arrays;

public class ResearchGroups {
    
    /**
     * Helper method used to merge the two subarrays created in sort that are passed in as params
     * This method takes merges the subarrays based on order and compares to elements of the two subarrays
     * @param left @code int[]} The integer subarray to be merged together with right into result
     * @param right @code int[]} The integer subarray to be merged together with left into result
     */
    public static int[] merge(int left[], int right[]){
        // lengths of the subarrays
        int lLength = left.length;
        int rLength = right.length;
        int[] result = new int[lLength + rLength];

        int i = 0;
        // while the arrays are not empty, copy over the subarrays in order depending on which values are the smallest
        while(lLength > 0 && rLength > 0){
            if(left[0] <= right[0]){
                result[i] = left[0];
                left = Arrays.copyOfRange(left, 1, lLength);
                lLength = left.length;
            } else{
                result[i] = right[0];
                right = Arrays.copyOfRange(right, 1, rLength);
                rLength = right.length;
            }
            i++;
        }

        // If there are still elements left in left subarray, then copy those over to the resulting array
        if(lLength > 0){
            for(int j = 0; j < lLength; j++){
                result[i] = left[j];
                i++;
            }
        }
        // If there are still elements left in right subarray, then copy those over to the resulting array
        if(rLength > 0){
            for(int k = 0; k < rLength; k++){
                result[i] = right[k];
                i++;
            }
        }
        return result;

    }

    /**
     * The merge sort procedure, merges the sorted subarrays together to result in the final sorted numbers array
     * @param numbers   {@code int[]} The integer array to be sorted
     */
    public static int[] sort(int[] numbers) {
        // lengths of arrays
        int arrayLength = numbers.length;
        int[] left = new int[arrayLength/2];
        int[] right = new int[arrayLength - arrayLength/2];

        // TODO: Lab 1 -- write mergesort here
        // if array is only one element then return numbers, else find the middle point of the array and copy the two sides into
        // left and right 
        if(arrayLength <= 1){
            return numbers;
        } else{
            int middle = numbers.length/2;
            for(int i = 0; i < middle; i++){
                left[i] = numbers[i];
            }
            for(int j = middle; j < numbers.length; j++){
                //System.out.println("In second loop");
                right[j-middle] = numbers[j];
            }

            // recursively calling sort, until length is 1 and they can be merged back together
            left = sort(left);
            right = sort(right);

            if(left[left.length-1] <= right[0]){
                int[] finalArray = new int[left.length + right.length];
                System.arraycopy(left, 0, finalArray, 0, left.length);
                System.arraycopy(right, 0, finalArray, left.length, right.length);
                return finalArray;
            }
            
            int[] mergedArray = new int[left.length + right.length];
            mergedArray = merge(left, right);
            System.arraycopy(mergedArray, 0, numbers, 0, numbers.length);
        }


	    return numbers;
    }
    /**
     * Search a person to check whether he/she is in the groups
     * @param groups    {@code String[]} The 2D array of groups to be searched
     * @param name      {@code String} name of the person
     */
    public static void searchMember(String[][] groups, String name) {
        // TODO: Lab 2 Part 1-1 -- search and print the results here
        boolean memberFlag = false;
        System.out.println("Member = "+name);

        for(int i = 0; i < groups.length; i++){
            for(int j = 0; j < groups[i].length; j++){
                if(groups[i][j].equals(name)){
                    System.out.println("Member is in a group? Yes");
                    System.out.println("Member group number: " + (i));
                    if(j == 0){
                        System.out.println("Member group leader? Yes");
                    } else{
                        System.out.println("Member group leader? No");
                    }
                    memberFlag = true;
                }
                                
            }
        }
        if(!memberFlag){
            System.out.println("Member is in a group? No");
            System.out.println("Member group number: N/A");
            System.out.println("Member group leader? N/A");
        }
        System.out.println("------------------------------------");

    }

    /**
     * Sort groups by number of members <br />
     * @param groups    (<code>String[][]</code>) The 2D array of groups to be sorted
     */
    public static void sortGroups(String[][] groups) {
        // TODO: Lab 2 Part 1-2 -- sort and print the results here. Reuse your heapsort
        int hashedGroups[] = new int[groups.length];
        for(int i = 0; i < groups.length; i++){
            
            hashedGroups[i] = groups[i].length*100 + i;
        }
        hashedGroups = sort(hashedGroups);

        for(int i = 0; i < hashedGroups.length; i++){
            int index = hashedGroups[i] % ((int)Math.floor(hashedGroups[i]/100)*100);
            for(int j = 0; j < groups[index].length; j++){
                if((j+1) == groups[index].length){
                    System.out.println(groups[index][j]);
                } else{
                    System.out.print(groups[index][j] + ", ");
                }
            }
        }
        
    }

    /**
     * Main entry
     * @param args      {@code String[]} Command line arguments
     */
    public static void main(String[] args) {
        String[][] groups = { {"Bob", "Carol", "Eric", "Matt"},             // 0
                              {"Jim", "Lucy", "Terry", "Brenda", "Ben"},    // 1
                              {"Susan", "Brad", "Jim"},                     // 2
                              {"Sue", "Wendy", "Sam"},                      // 3
                              {"Kate", "Jack", "James", "Sydney"},          // 4
                              {"Mohammad", "Tim", "Kian"},                  // 5
                              {"Emma", "Carol"},                            // 6
                              {"Nick", "Osama", "Harry", "Ben"},            // 7
                              {"Mary", "John", "Ricky"} };                  // 8

        ResearchGroups.searchMember(groups, "Jim");
        ResearchGroups.searchMember(groups, "Lucy");
        ResearchGroups.searchMember(groups, "John Doe");
        ResearchGroups.sortGroups(groups);
    }

}
