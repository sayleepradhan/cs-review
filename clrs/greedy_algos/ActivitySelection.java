package clrs.greedy_algos;

import java.util.*;

public class ActivitySelection {

    private Set<Activity> recursiveActivitySelector(Set<Activity> schedule,
                                           ArrayList<Activity> activities,
                                           int finishTime,
                                           int lastActivityIndex) {
        int newIndex = lastActivityIndex + 1;
        int newStartTime = finishTime + 1;
        while (newIndex < activities.size()
                && activities.get(newIndex).startTime < newStartTime) {
            newIndex += 1;
        }
        if (newIndex < activities.size()) {
            Activity toAdd =  activities.get(newIndex);
            if (schedule == null) {
                schedule = new HashSet<>();
            }
            schedule.add(toAdd);
            schedule.addAll(
                    recursiveActivitySelector(schedule, activities, toAdd.finishTime, newIndex)
            );
        }
        return schedule;
    }

    private void sortActvities(List<Activity> activities) {
        activities.sort((o1, o2) -> {
            if (o1.finishTime == o2.finishTime)
                return 0;
            return o1.finishTime > o2.finishTime ? 1 : -1;
        });
    }

    public static void main(String[] args) {
        ActivitySelection solution = new ActivitySelection();

        ArrayList<Activity> activities = new ArrayList<>(Arrays.asList(
                new Activity(12, 16),
                new Activity(2, 14),
                new Activity(1, 4),
                new Activity(8, 12),
                new Activity(3, 5),
                new Activity(0, 6),
                new Activity(8, 11),
                new Activity(6, 10),
                new Activity(5, 7),
                new Activity(3, 9),
                new Activity(5, 9))
        );

        solution.sortActvities(activities);
        System.out.println("List of all the activities: ");
        for (Activity a : activities) {
            System.out.println ("Activity "+activities.indexOf(a)+":- start: "+a.startTime+" finish: "+a.finishTime);
        }

        System.out.println();
        System.out.println("List of the activities selected in the schedule: ");
        Set<Activity> schedule = solution.recursiveActivitySelector(null, activities,0,-1);

        activities.clear();
        activities.addAll(schedule);
        solution.sortActvities(activities);

        for (Activity a: activities) {
            System.out.println ("Activity "+activities.indexOf(a)+":- start: "+a.startTime+" finish: "+a.finishTime);
        }
    }
}

class Activity {
    int startTime;
    int finishTime;

    Activity(int s, int f) {
        this.startTime = s;
        this.finishTime = f;
    }
}