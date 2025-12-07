/**
 * @param {Function} fn
 * @return {Function}
 */
function memoize(fn) {
    // The root of our Trie
    const root = new Map();
    
    // A unique Symbol to mark the return value inside the Map.
    // We use a Symbol so it never clashes with a valid argument key.
    const RESULT_KEY = Symbol('result');

    return function(...args) {
        let currentMap = root;

        // 1. Traverse the Trie based on arguments
        for (const arg of args) {
            if (!currentMap.has(arg)) {
                // If path doesn't exist, create a new branch
                currentMap.set(arg, new Map());
            }
            // Move down the tree
            currentMap = currentMap.get(arg);
        }

        // 2. Check if we already have a result at this leaf node
        if (currentMap.has(RESULT_KEY)) {
            return currentMap.get(RESULT_KEY);
        }

        // 3. Compute, Cache, and Return
        const result = fn(...args);
        currentMap.set(RESULT_KEY, result);
        
        return result;
    }
}