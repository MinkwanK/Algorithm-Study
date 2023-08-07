package algo_Study;

import java.util.Scanner;

/*
 * �������� ���� ��ġ {{ 1,1 } { 1,2 } }
 * ��,��,���� ���θ� �̵� ����
 * �������� ���� ���� (N,N)���� �̵�������� �Ѵ�.
 * 
 * ����: ��, ���� 
 * ����: ��, ����
 * �밢��: ����, ����, ����
 */
public class bjo17070_�������ű��1 {

	// �������� x,y ��ǥ
	// static int x, y;
	static int n;
	static int[][] delta = { { 1, 1 }, { 0, 1 }, { 1, 1 } }; // ����,����,����
	static int[][] map;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// x = 1;
		// y = 2; // �ʱ� ������ ��ġ

		for (int i = 1; i <= 3; i++) {
			func(i, 1, 2);
		}

	}

	/*
	 * �������� ��� mode 1 : ���� mode 2 : ���� mode 3 : �밢�� -------�ε����� 1���� �����Ѵ�---- �밢������ ��
	 * �ٰ��� �ȵǸ� ���ƿͼ� �ٽ� �б� ����
	 */
	static void func(int mode, int x, int y) {

		if (x == n && y == n) {
			System.out.println("����");
		} else {
			System.out.println("����  " + "X: " + x + "  Y :" + y + "  Mode: " + mode);
		}

		if (mode == 1) {
			for (int i = 0; i < 2; i++) {
				int nextX = x + delta[i][0];
				int nextY = y + delta[i][1];

				if (i == 0) {
					if (nextX < 1 || nextX > n || nextY < 1 || nextY > n || map[nextX][nextY] == 1) { // �� ������
																										// return
						return;
					}
				} else {
					if (nextX < 1 || nextX > n || nextY < 1 || nextY > n || map[nextX][nextY] == 1
							|| map[nextX][nextY - 1] == 1 || map[nextX - 1][nextY - 1] == 1) {
						// �� ������ return
						return;
					}
				}

				x = nextX;
				y = nextY;
				mode = i + 1;

				func(mode, x, y);

			}
		} else if (mode == 2) {
			for (int i = 0; i < 2; i++) {
				int nextX = 0;
				int nextY = 0;

				if (i == 1) {
					nextX = x + delta[2][0];
					nextY = y + delta[2][1];
				} else {
					nextX = x + delta[i][0];
					nextY = y + delta[i][1];
				}

				if (i == 0) {
					if (nextX < 1 || nextX > n || nextY < 1 || nextY > n || map[nextX][nextY] == 1) { // �� ������
																										// return
						return;
					}
				} else {
					if (nextX < 1 || nextX > n || nextY < 1 || nextY > n || map[nextX][nextY] == 1
							|| map[nextX][nextY - 1] == 1 || map[nextX - 1][nextY - 1] == 1) {
						// �� ������ return
						return;
					}
				}

				x = nextX;
				y = nextY;
				mode = i + 1;

				func(mode, x, y);
			}
		} else {

			for (int i = 0; i < 3; i++) {
				int nextX = x + delta[i][0];
				int nextY = y + delta[i][1];

				if (i == 0 || i == 1) {
					if (nextX < 1 || nextX > n || nextY < 1 || nextY > n || map[nextX][nextY] == 1) { // �� ������
																										// return
						return;
					}
				} else {
					if (nextX < 1 || nextX > n || nextY < 1 || nextY > n || map[nextX][nextY] == 1
							|| map[nextX][nextY - 1] == 1 || map[nextX - 1][nextY - 1] == 1) {
						// �� ������ return
						return;
					}
				}

				x = nextX;
				y = nextY;

				mode = i + 1;

				func(mode, x, y);
			}
		}
	}

}
