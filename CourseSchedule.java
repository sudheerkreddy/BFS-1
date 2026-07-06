
// Time complexity: O(V+E)
// space complexity: O(V+E)
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for(int[] prereq: prerequisites) {
            int ind = prereq[1];
            int dep =  prereq[0];
            indegree[dep]++;

            if(map.get(ind)== null) {
                map.put(ind, new ArrayList<>());    
            }
            map.get(ind).add(dep);
        }

        Queue<Integer> q = new LinkedList<>();
        int result = 0;    
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                q.add(i);
                result++;
            }
        }

        if (q.isEmpty()) return false;
        if (result == numCourses) return true;

        while(!q.isEmpty()) {
            int curr = q.poll();
            List<Integer> dependencies = map.get(curr);

            if(dependencies != null) {
                
                for(int dependent: dependencies) {
                    indegree[dependent]--;

                    if(indegree[dependent] == 0) {
                        q.add(dependent);
                        result++;
                        if(result == numCourses) return true;
                    }
                }
            }
        }
        return false;
    }
}