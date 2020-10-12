import java.util.Random;

public class Lab1{
	public static void main(String[] args){
		final int size1 = 16;
		final int size2 = 17;
		final float left = -6.0f;
		final float right = 14.0f;
		short[] d = new short[size1];
		for(short i = 2; i < size1 + 2; i++){
			d[i - 2] = i;
		}
		float[] x = new float[size2];
		for (int i = 0; i < size2; i++){
			x[i] = RandomInArray(left, right);
		}
		double[][] l = new double[size1][size2];
		for(int i = 0; i < size1; i++){
			for(int j = 0; j < size2; j++){
				if (d[i] == 7){
					l[i][j] = (float)Math.pow((0.25 * (3 - 3 * Math.cos(Math.cos(x[j])))), Math.tan(Math.cos(x[j])));
				}
				else if (InArray(d[i])){
					l[i][j] = (float)Math.atan(((x[j] + 4) * Math.E)/2 + 1);
				}
				else{
					l[i][j] = (float)Math.pow(((Math.atan(Math.cos(Math.asin(((x[j] + 4) * Math.E)/2 + 1))) + 0.75) / (Math.log(Math.abs(Math.cos(x[j]))))), Math.pow(Math.tan(Math.pow((x[j] / 2.0), 3)),Math.atan(Math.cos(x[j]))));
					
				}
			}
		}
		PrintArray(l, size1, size2);
	}
	public static boolean InArray(short k){
		boolean c = false;
		short[] arr = {3, 6, 9, 10, 13, 14, 16, 17};
		for(int i = 0; i < 8; i++){
			if(arr[i] == k){
				c = true;
			}
		}
		return c;
	}
	public static float RandomInArray(float left, float right){
		Random r = new Random();
		float len = right - left;
		float x = r.nextFloat() * len + left;
		return x;
	}
	public static void PrintArray(double[][] l, int size1, int size2){
		for(int i = 0; i < size1; i++){
			for(int j = 0; j < size2; j++){
				System.out.printf("%.3f ", l[i][j]);
			}
			System.out.print("\n");
		} 
	}
}