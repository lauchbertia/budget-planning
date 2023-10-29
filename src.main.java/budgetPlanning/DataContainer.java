package budgetPlanning;

import java.util.List;

/**
 * DataContainer for GSON data
 * @see Data
 * @see HandleData
 * 
 * @return List of data
 */

public class DataContainer {
	private List<HandleData> data;

    public List<HandleData> getData() {
        return data;
    }
}

