package com.techdevsubhopriyo.toolbox;

import java.util.ArrayList;
import java.util.Collections;

public class Apriori {
    /**
     * Apriori algorithm for Frequent Data Mining
     * @param transactions will contain all the transactions that have taken earlier
     * @param minSupportCount will contain the min support count for the transactions
     * @author Subhopriyo Sadhukhan
     */
    public static ArrayList<ArrayList<String>> algorithm(
            ArrayList<ArrayList<String>> transactions,
            int minSupportCount
    ){
        ArrayList<ArrayList<String>> prevItemSetsWithMinSupportCount = new ArrayList<ArrayList<String>>();
        ArrayList<String> items = getUniqueItems(transactions);

        int x = 0;
        while (true) {
            x++;
            ArrayList<Integer> supportCountList = new ArrayList<Integer>();
            ArrayList<ArrayList<String>> itemSets = getItemSets(items, x);

            for (ArrayList<String> itemSet : itemSets) {

                int count = 0;
                for (ArrayList<String> transaction : transactions) {
                    if (existsInTransaction(itemSet, transaction)) count++;
                }
                supportCountList.add(count);
            }
            ArrayList<ArrayList<String>> itemSetsWithMinSupportCount = getItemSetsWithMinSupportCount(itemSets, supportCountList, minSupportCount);

            if (itemSetsWithMinSupportCount.size() == 0) {
                return prevItemSetsWithMinSupportCount;
            }
            items = getUniqueItems(itemSetsWithMinSupportCount);
            prevItemSetsWithMinSupportCount = itemSetsWithMinSupportCount;
        }
    }

    private static ArrayList<String> getUniqueItems (ArrayList<ArrayList<String>> data) {
        ArrayList<String> toReturn = new ArrayList<String>();

        for (ArrayList<String> transaction : data) {
            for (String item : transaction) {
                if (!toReturn.contains(item)) toReturn.add(item);
            }
        }
        Collections.sort(toReturn);
        return toReturn;
    }

    private static ArrayList<ArrayList<String>> getItemSets (ArrayList<String> items, int number) {
        if (number == 1) {
            ArrayList<ArrayList<String>> toReturn = new ArrayList<ArrayList<String>>();
            for (String item : items) {
                ArrayList<String> aList = new ArrayList<String>();
                aList.add(item);
                toReturn.add(aList);
            }
            return toReturn;
        } else {
            int size = items.size();
            ArrayList<ArrayList<String>> toReturn = new ArrayList<ArrayList<String>>();
            for (int i = 0; i < size; i++) {
                ArrayList<String> _items = new ArrayList<String>();
                for (String item : items) {
                    _items.add(item);
                }

                String thisItem = items.get(i);
                for (int j = 0; j <= i; j++) {
                    _items.remove(0);
                }
                ArrayList<ArrayList<String>> permutationsBelow = getItemSets(_items, number - 1);

                for (ArrayList<String> aList : permutationsBelow) {
                    aList.add(thisItem);
                    Collections.sort(aList);
                    toReturn.add(aList);
                }
            }
            return toReturn;
        }
    }

    private static boolean existsInTransaction (ArrayList<String> items, ArrayList<String> transaction) {
        for (String item : items) {
            if (!transaction.contains(item)) return false;
        }
        return true;
    }

    private static ArrayList<ArrayList<String>> getItemSetsWithMinSupportCount (
            ArrayList<ArrayList<String>> itemSets, ArrayList<Integer> count, int minSupportCount) {

        ArrayList<ArrayList<String>> toReturn = new ArrayList<ArrayList<String>>();
        for (int i = 0; i < count.size(); i++) {
            int c = count.get(i);
            if (c >= minSupportCount) {
                toReturn.add(itemSets.get(i));
            }
        }
        return toReturn;
    }
}