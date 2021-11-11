

public class Matrix {
	
	//comment
    
	private int[][]  matrixData;// integer array to store integer data
	private int    rowsNum;	//# of rows
	private int    colsNum;	// # of columns
	
	public Matrix( int row, int col ) //1st constructor
	{   
		/*
		* constructs a row-by-col matrix with
		* all elements equal to 0; if row <= 0, the number of rows of the matrix is set to
		* 3; if col <= 0 the number of columns of the matrix is set to 3.		
		*/
		this.rowsNum = row;
		this.colsNum = col;
		
		if(row<=0) {
			this.rowsNum = 3; //change row number if greater than or less than 0 to 3
		}
		
		if(col<=0){//change column number if greater than or less than 0 to 3
			this.colsNum = 3;
		}
		
		matrixData = new int [this.rowsNum][this.colsNum];//define array size
			
	
	}

	public Matrix( int[][] table) //2nd constructor
	{	

		/*
		* constructs a matrix out of the two dimensional array table, with the same number of rows, columns, and the same
		* element in each position as array table.
		*/ 
	
		this.rowsNum = table.length;
		this.colsNum = table[0].length;
		this.matrixData = new int[rowsNum][colsNum];//allocate memory for 2D array
		
		for(int i = 0; i<rowsNum; i++){//Nested for loop implemented
			for(int j= 0; j<colsNum; j++){//to fill the array with row and column values
				this.matrixData[i][j] = table[i][j];
			}
		}
	}
	
	public int getElement(int i, int j) throws IndexOutOfBoundsException
	{ 	
		/*
		* returns the element on row i and column j of this matrix; 
		* throws an exception (IndexOutOfBoundsException) if any of indexes i and j is not in required range 
		*/
		if(i<0 || i> this.rowsNum ){// if rows are out of bounds
			throw new  IndexOutOfBoundsException("Invalid indexes (for rows)");//error message for rows
		}
		
		if(j<0 || j> this.colsNum ) {
			throw new  IndexOutOfBoundsException("Invalid indexes (for columns)");//error message for columns
		}
		
		return matrixData[i][j];//return specific array value
	}
        
    public boolean setElement(int x, int i, int j){ 
            
    	if(i<0 || i> this.rowsNum ) {// if rows are out of bounds
    		return false; 
    	}
    	if(j<0 || j> this.colsNum ) {// if columns are out of bounds
    		return false;
    	}
    	matrixData[i][j] = x; //change matrix value to x
    	
    	return true; 
    } 

    public Matrix copy(){ 
                
    	return  new Matrix(matrixData); //returns copy of matrix
    }    
                
	public void addTo( Matrix m ) throws ArithmeticException
	{		
		if(this.rowsNum != m.rowsNum || this.colsNum != m.colsNum) {
			throw new  ArithmeticException("Invalid indexes");		//if rows or columns of matrices are not the same
		}//throw exception
	
		for(int i = 0; i<rowsNum; i++)//Nested for loop implemented
			for(int j= 0; j<colsNum; j++) //to fill the array with row and column values
				 this.matrixData[i][j] += m.matrixData[i][j];//add same position elements of matrices together to get new matrix
			}
		
	
	
    public Matrix subMatrix(int i, int j) throws ArithmeticException{ 
        		
    	if(i<0 || i> this.rowsNum || j<0 || j> this.colsNum) {//if rows and columns are out of bounds
    		throw new  ArithmeticException("Submatrix not defined");//throw exception
    	}
    	
    	Matrix subM = new Matrix (i,j);//
    	
    	for(int row = 0; row<i; row++) {//iterates through entire matrix to populate new sub matrix subM
    		for(int col = 0; col<j; col++) {
    			subM.setElement(matrixData[row][col], row, col);//call setElement 
    		}													
    	}
    	
        return  subM; //return the sub matrix
        
    }
        
    public int getsizeofrows(){ 
           
		return rowsNum;//accessor for rows
    }
        
    
    public boolean isSquare() {
		return rowsNum == colsNum;
	}
    
    
    public int getsizeofcols(){
		
        return colsNum; //accessor for columns
    }
        
    public boolean isUpperTr(){
            
        int increase = 1; 
    	for(int i = 1; i<rowsNum;i++) {//i is one as first row is never 0 for upper triangular matrix
    		for(int j = 0; j<increase;j++) {//iterates through matrix
    			if(matrixData[i][j] != 0) { //to check if elements below main diagonal are 0
    				return false;//if elements below diagonal aren't 0 return false
    			}
    			
    		}
    		if(increase!= colsNum) {//increments variable until equals # of columns
    			increase++;
    		}
    	}
    	
    	return true;  
	}
        
    public static Matrix sum(Matrix[] matArray) throws ArithmeticException{
        
    	Matrix superMatrix = matArray[0].copy();//copy first matrix of array into a new object
        
    	for(int i = 1; i<matArray.length;i++ ) {
    		superMatrix.addTo(matArray[i]);//Goes through every element from the input array and adds it to matrix
    	}									
            
        return superMatrix; 
    }
        
	public String toString(  )
	{
		String output = new String();
		
		for(int i = 0; i<rowsNum; i++) {//iterates through all rows and columns
			for(int j=0; j<colsNum;j++) {
				output += matrixData[i][j] + " "; //puts matrix values in a string with a space between columns
			}
		
			output += "\n"; //add new line after every row
		}
        	
		return output;
	}
	
}