public class FireModel
{
    public static int SIZE = 47;
    private FireCell[][] myGrid;
    private FireView myView;

    public FireModel(FireView view)
    {
        myGrid = new FireCell[SIZE][SIZE];
        int setNum = 0;
        for (int r=0; r<SIZE; r++)
        {
            for (int c=0; c<SIZE; c++)
            {
                myGrid[r][c] = new FireCell();
            }
        }
        myView = view;
        myView.updateView(myGrid);
    }

    /*
        recursiveFire method here
     */
    
    public void recursiveBurn(int r, int c) {
    	if(r < 0 || r >= SIZE || c < 0 || c >= SIZE) {
    		return;
    	}
    	
    	if(myGrid[r][c].getStatus() != FireCell.GREEN) {
    		return;
    	}
    	
    	myGrid[r][c].setStatus(FireCell.BURNING);
    	myView.updateView(myGrid);
    	
    	try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	recursiveBurn(r + 1, c);
    	recursiveBurn(r, c + 1);
    	recursiveBurn(r - 1, c);
    	recursiveBurn(r, c - 1);
    	
    
    }

    public void solve()
    {
        // student code here
    	
    	for(int c = 0; c < SIZE; c++) {
    		recursiveBurn(SIZE - 1,c);
    	}
    	
    	
    	boolean safe = true;
    	for(int c = 0; c < SIZE; c++) {
    		if(myGrid[0][c].getStatus() == FireCell.BURNING) {
    			safe = false;
    			break;
    		}
    	}
    	
    	if(safe)
    		System.out.println("Onett is safe");
    	else 
    		System.out.println("Onett is in danger");
    	
        myView.updateView(myGrid);
    }

}
