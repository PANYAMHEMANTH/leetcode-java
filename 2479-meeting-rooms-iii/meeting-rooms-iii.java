class Solution {
    public int mostBooked(int n, int[][] meetings) {
        // Sort meetings by start time
        Arrays.sort(meetings, (a, b) -> Integer.compare(a[0], b[0]));
        
        // Min-heap for available room numbers (smallest room first)
        PriorityQueue<Integer> availableRooms = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            availableRooms.offer(i);
        }
        
        // Min-heap for busy rooms: (endTime, roomNumber)
        // We want earliest ending meeting first
        PriorityQueue<long[]> busyRooms = new PriorityQueue<>(
            (a, b) -> a[0] == b[0] ? Long.compare(a[1], b[1]) : Long.compare(a[0], b[0])
        );
        
        // Count of meetings per room
        int[] meetingCount = new int[n];
        
        for (int[] meeting : meetings) {
            int start = meeting[0];
            int end = meeting[1];
            long duration = end - start;
            
            // Free up all rooms that are done by the current start time
            while (!busyRooms.isEmpty() && busyRooms.peek()[0] <= start) {
                long[] finished = busyRooms.poll();
                int room = (int) finished[1];
                availableRooms.offer(room);
            }
            
            int roomToUse;
            long actualStart;
            
            if (!availableRooms.isEmpty()) {
                // Use the smallest available room
                roomToUse = availableRooms.poll();
                actualStart = start;
            } else {
                // No room available → use the one that finishes earliest
                long[] earliest = busyRooms.poll();
                actualStart = earliest[0];  // meeting starts when this room frees up
                roomToUse = (int) earliest[1];
            }
            
            // Schedule the meeting
            long actualEnd = actualStart + duration;
            busyRooms.offer(new long[]{actualEnd, roomToUse});
            meetingCount[roomToUse]++;
        }
        
        // Find the room with maximum meetings (tiebreak by smallest room number)
        int maxMeetings = 0;
        int resultRoom = 0;
        
        for (int i = 0; i < n; i++) {
            if (meetingCount[i] > maxMeetings) {
                maxMeetings = meetingCount[i];
                resultRoom = i;
            }
        }
        
        return resultRoom;
    }
}