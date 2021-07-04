package algoritmos;



public class NeedlemanWunsch {


	
	
	public String nombre="Needleman-Wunsch";
	
	public static  Double similitud;
	
    public NeedlemanWunsch() {
        super();
    }
    
    
    public boolean comprobar(String _nombre) {
    	if (nombre.equals(_nombre)) {
    		return true;
    	}else {
    		return false;
    	}
    }
	
	
    static int l=0;

	public double calcularSimilitudNeedlemanWunsch(String a, String b) {
		//similitud=calcularNeedlemanWunsch(a, b);
		return similitud;
	}
	
    
 /*   public static double calcularNeedlemanWunsch(String t, String p) {


    	//Create an H matrix for scoring and become a scoring matrix, and a D matrix for backtracking and become a pointer matrix or a direction matrix

       int tlen=t.length();

       int plen=p.length();

       int [][] h=new int[tlen+1][plen+1];

       int [][] d=new int[tlen+1][plen+1];

       //Initialize the matrix, the first column or the behavior of the latter insert, deduct 2

       for(int i=0;i<1;i++){
           for(int j=0;j<plen+1;j++){
               h[i][j]=-2*j;
               d[i][j]=3;
           }
       }    

       for(int j=0;j<1;j++){
           for(int i=0;i<tlen+1;i++){
               h[i][j]=-2*i;
               d[i][j]=1;
           }
       }

       //Dynamic planning is used for scoring

       for(int i=1;i<tlen+1;i++){
           for(int j=1;j<plen+1;j++){
        	   //Score: mismatch -1, deletion/inserting-2, match 1,
               int  s1=-2,s2=0,s3=-2;
               if(t.charAt(i-1)==p.charAt(j-1)){
                   s2=1;
               }else{
                   s2=-1;
               }
               h[i][j]=maximum(h[i-1][j]+s1,h[i-1][j-1]+s2,h[i][j-1]+s3);
               d[i][j]=l;
           }
       }*/

       //Output scoring matrix
/*
       System.out.println("score matrix:");
       for(int i=0;i<tlen+1;i++){
           for(int j=0;j<plen+1;j++){
               System.out.printf("%4d",h[i][j]);
               if(j!=0&&j%plen==0){
                   System.out.println();
               }
           }
       }

       //Output index matrix

/*       System.out.println("index matrix:");
       for(int i=0;i<tlen+1;i++){
           for(int j=0;j<plen+1;j++){
               System.out.print(d[i][j]+" ");
               if(j!=0&&j%plen==0){
                   System.out.println();
               }
           }
       }
*/
/*Output result*/

   /*     System.out.print("Target sequence:");

       String result =get_back(t,p,d);

       for (int i=0;i<result.length();i++){

           System.out.print(result.charAt(i)+" ");

       }

       System.out.println();

       System.out.print("Source sequence:");

       for (int i=0;i<p.length();i++){

           System.out.print(p.charAt(i)+" ");

       }

    }*/

/*How to find the maximum value*/

    public static int maximum(int a,int b,int c){

        int max =a;
        l=1;
        if(a<b){
            max=b;
            l=2;
            if(b<c){
                max=c;
                l=3;
            }
        }else if(a<c){
            max=c;
            l=3;
        }
        if(max==a&&max==b){
            l=4;
        }else if(max==a&&max==c){
            l=5;
        }else if(max==b&&max==c){
            l=6;
        }
        if(max==a&&max==b&&max==c){
        l=7;
        }
        return max;

    } 

/*Backtracking method*/

     public static  String get_back(String t,String p,int[][] d){

         int i=t.length();
         int j=p.length();
         StringBuffer sb = new StringBuffer();
         while(i>=0&&j>0){
         int start = d[i][j];
         switch(start){
             case 1:sb.insert(0, t.charAt(i-1));i=i-1;break;
             case 2:sb.insert(0, t.charAt(i-1));i=i-1;j=j-1;break;
             case 3:sb.insert(0, '-');j=j-1;break;
             case 4:sb.insert(0, t.charAt(i-1));i=i-1;j=j-1;break;
             case 5:sb.insert(0, t.charAt(i-1));i=i-1;break;
             case 6:sb.insert(0, t.charAt(i-1));i=i-1;j=j-1;break;
             case 7:sb.insert(0, t.charAt(i-1));i=i-1;j=j-1;break;
              }
         }     
         String result =sb.toString();
         return result;    

     }    

}