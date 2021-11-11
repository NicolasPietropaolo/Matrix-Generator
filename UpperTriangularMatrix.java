
public class UpperTriangularMatrix {
    private int n;
    private int[] array;
    
    public UpperTriangularMatrix(int n){
      
    	this.n  =  n;//define size of matrix
    	
    	if(this.n<=0) {
    		this.n = 1;//change amount of columns and rows to one if they are a negative or 0 value
    	}
    	
       this.array = new int [this.n*(this.n+1)/2];//create the one dimensional array
           
    }
    
    public UpperTriangularMatrix(Matrix upTriM) throws IllegalArgumentException{
       
    	  int temprows =  upTriM.getsizeofrows();//temporary variables for function of number of columns and rows of matrix 
          
          int tempcols = upTriM.getsizeofcols();
    	
    	if(!upTriM.isUpperTr()) {//call isUpperTr method and  check if input is not upper triangle
    	    throw new IllegalArgumentException("Not an upper triangle matrix");//throw exception
       }
       
       if(temprows>tempcols) {
    	   this.n = temprows;//if larger number of rows use row size for matrix dimensions
    	   
       }
       else if(tempcols> temprows){//if larger number of columns use column size for matrix dimensions
    	   
    	   this.n = tempcols;
       }
       else{
    	   this.n =temprows;//if rows and columns are equal can use either rows or columns for dimensions
       }
    
     
       
       int indx = 0; 
       array = new int [this.n*(this.n+1)/2];//initialize array size
       
       for(int i =0; i<temprows; i++) {// populates array
    	   for(int j = i; j<tempcols; j++ ) {
    		   this.array[indx] = upTriM.getElement(i, j);
    		   indx++;
    	   }
       }
          
    }    
        
    public int getDim(){
               
    	return this.n; //return number of rows
    }
    
    public int getElement(int i, int j) throws IndexOutOfBoundsException{
		    	
    	if(i<0 || i<this.n || j<0 || j< this.n) {// verify columns and rows of matrix are in range 
    		throw new IndexOutOfBoundsException("Invalid index"); //throw exception
    	}
    	
    	int position = getPos(i,j); //call position getter function
		
        return array[position]; //return element in array
    }
    
    public void setElement(int x, int i, int j) throws IndexOutOfBoundsException,IllegalArgumentException{
       
    	if(i>j && x!= 0) {//if statement to throw exception if values are below main diagonal and are not equal to 0
    		throw new IllegalArgumentException("Incorrect Arguement");//throws exception
    		
    	}
    	
    	if(i<0 || i<this.n || j<0 || j< this.n) {//if statement to verify columns and rows of matrix are in range 
    		throw new IndexOutOfBoundsException("Invalid index"); //throw exception
    	}
   
    	 int position= getPos(i,j) ; // call function to get position
    	 
    	 array[position] = x; //change element in position to x
    
    }
    
    
    public int getPos(int i, int j) {
    	
    	int k =0;//define new variable
    	
		if(j<i) {//return 0 for values below main diagonal 
    		return 0;
    	}
		
    	else {
    		for(int row =0; i>row; row++) {//for loop that skips appropriate amount of rows
    			k += this.n-row;
    			
    		}
    		k+= j-i; //calculation to go to correct column
    	
    	}
    
		return k;
    }
    
    public Matrix fullMatrix(){

        Matrix full= new Matrix(n,n); 
        
        int increment = 0;
        
        for(int i=0; i<this.n; i++) {// convert the 1D array into a matrix
        	for(int j=0; j<this.n; j++) {
        		if(full.setElement(this.array[increment], i, j)) {
        			increment++;
        			
        		}
        		
        	}
        }

        return full; 
    }
    
   
    public String toString(){
    	
       return fullMatrix().toString(); //call functions that converts to 2D array and convert it to a string 
    }
    
    public int getDet(){
    	int answer = 1, indx = 0;
        
        for (int i = 0; i < this.n; i++) {
            answer *= this.array[indx];//to multiply all diagonal values together
            indx += this.n - i;
        }
        
       return answer; 
    }

    public void arrayPrint() {//prints 1D arrays
        String result = new String();
        for (int i = 0; i < array.length; i++)// goes through length of array
            result += array[i] + "  ";
        System.out.println(result);
    }
    
    
    
    
    public double[] effSolve(double[] b) throws IllegalArgumentException{

    	 if (getDet() == 0) { throw new IllegalArgumentException("The determinant of the matrix is 0"); }//using getDet function to see if determinant of matrix is 0
         else {
             if (b.length == this.n) {//equal to amount of rows/columns
                 double[] a = new double[this.n];

                 
                 int indx;
                 for (int i = this.n - 1; i >= 0; i--) {//starts from last row 
                     double sum = 0; //back substitution algorithm
                     for (int j = i + 1; j <= this.n - 1; j++) {//iterates until the last column 
                         indx = 0;
                         for (int k = 0; k < i; k++)
                             indx += this.n - k;
                         indx += j - i;
                         sum += this.array[indx] * a[j];
                     }
                     indx = 0;
                     for (int k = 0; k < i; k++)
                         indx += this.n - k;
                     a[i] = (b[i] - sum) / this.array[indx];
                 }
                 return a;
             }
             else { throw new IllegalArgumentException("The dimension of the matrix is not defined for the operation"); }//if dimeansions aren't defined properly
         }
     }
 }
