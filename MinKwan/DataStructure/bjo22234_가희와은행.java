package algo_Study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * ���� ���� ������ �����ϸ� ����ٿ��� �մ��� N�� �ִ�.
 * ������� x�� �մԿ� ���� ������ id ���� ������ �ʿ��� �ð����� ��������.
 * ������ ������ �����ϰ� �� �Ŀ� ������ M���� �մ��� ����(�̸� �ͼ� ����ϴ� N��� �ٸ���) 
 * �� M���� ������� ����� �ڿ� ������� ��ġ�ȴ�. M���� �մ��� ���� ���� x�� �ڿ� ���Դٴ� ������ ����
 * 
 * ��� ť�� �� �տ� �ִ� ���� tx�� T���� ũ�� tx-T�� ��Ŵ. �׸��� �� �ڷ� �̵���Ų��. 
 * tx�� T���� �۴ٸ� T-tx �� �ڿ� �� �մ��� ��������.
 * N: �ʱ� ����� �ο� T: ���� �ѹ��� �� �� �ִ� �ð� W: ���� ���� �ð�
 * px: �� id tx:���� �ʿ� �ð� cx:���� ���� �ð����κ��� cx�ʰ� ������ �� ���࿡ ����
 * 
 * ť�� ���� �� ����� ����
 * while �ݺ����� ���� w�� �� ������ �� ����
 */

public class bjo22234_��������� {

	// �մ� Ŭ����
	static class Customer {
		public int px;
		public int tx;
		public int cx;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(st.nextToken()); // ��⿭�� �մ� ��
		int t = Integer.parseInt(st.nextToken()); // ��� ���� �ð�
		int w = Integer.parseInt(st.nextToken()); // ���� ���� �ð�
		int m = 0;
		Queue<Customer> queue = new LinkedList<>();
		Queue<Customer> newCustomerQueue = new LinkedList<>();
		// ��⿭ ���
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			Customer customer = new Customer();

			customer.px = Integer.parseInt(st.nextToken());
			customer.tx = Integer.parseInt(st.nextToken());
			customer.cx = 0;

			queue.add(customer);

		}

		// �߰� �� ��⿭ ���
		st = new StringTokenizer(br.readLine());
		m = Integer.parseInt(st.nextToken());

		Customer[] customerAry = new Customer[m];
		// �߰� �� ��⿭ ���
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			Customer customer = new Customer();

			customer.px = Integer.parseInt(st.nextToken());
			customer.tx = Integer.parseInt(st.nextToken());
			customer.cx = Integer.parseInt(st.nextToken());

			customerAry[i] = customer;

		}

		for (int i = 0; i < m; i++) {
			for (int j = i + 1; j < m; j++) {
				if (customerAry[i].cx > customerAry[j].cx) {
					Customer temp = customerAry[i];
					customerAry[i] = customerAry[j];
					customerAry[j] = temp;
				}
			}
		}

		for (int i = 0; i < m; i++) {
			newCustomerQueue.add(customerAry[i]);
		}

		// -----------------���� �� ����----------------------

		// N: �ʱ� ����� �ο� T: ���� �ѹ��� �� �� �ִ� �ð� W: ���� ���� �ð�
		// px: �� id tx:���� �ʿ� �ð� cx:���� ���� �ð����κ��� cx�ʰ� ������ �� ���࿡ ����
		long time = 1;

		// ������ �� ���� ����

		while (w > 0) {

			// System.out.println("W: " + w + " Time : " + time);
			// ť�� ������� �ʰ�, ������ ü���� ���� �ִ� ���¶��

			if (queue.isEmpty() && newCustomerQueue.isEmpty())
				break;

			Customer next = newCustomerQueue.peek();

			if (!queue.isEmpty()) {

				// ť�� �� �� �մ��� �޾ƿ���
				Customer customer = queue.peek();

				// ���� �ð��� ���� ���� ���� �ð����� ũ�ų� ���ٸ�
				if (time >= customer.cx) {

					// System.out.println(customer.px);
					// ���� �ʿ��� �ð��� �� ������ �ð� �����ϰ� ť�� �� �ڷ� ������, ���� ������ ������~

					if (customer.tx > t) {

						if (w < t) {
							for (int i = 0; i < w; i++) {
								sb.append(customer.px + "\n");
							}
						} else {
							for (int i = 0; i < t; i++) {
								sb.append(customer.px + "\n");
							}
						}

						customer.tx -= t;
						time += t;
						w -= t;

						if (next != null && next.cx <= time) {
							{
								if (!newCustomerQueue.isEmpty()) {
									for (int i = 0; i < newCustomerQueue.size(); i++) {
										if (newCustomerQueue.peek().cx <= time) {
											queue.add(newCustomerQueue.poll());
										} else
											break;
									}
								}
							}
						}

						queue.add(customer);
						queue.remove();

					} else if (customer.tx <= t) {

						if (w < customer.tx) {
							for (int i = 0; i < w; i++) {
								sb.append(customer.px + "\n");
							}
						} else {
							for (int i = 0; i < customer.tx; i++) {
								sb.append(customer.px + "\n");
							}
						}

						w -= customer.tx;
						time += customer.tx;
						customer.tx = 0;

						queue.remove();

						if (next != null && next.cx <= time) {
							if (!newCustomerQueue.isEmpty()) {
								for (int i = 0; i < newCustomerQueue.size(); i++) {
									if (newCustomerQueue.peek().cx <= time) {
										queue.add(newCustomerQueue.poll());
									} else
										break;
								}
							}
						}

					}

				} else {
					w = (int) (w - ( customer.cx - time));
					time = customer.cx;
				}
				// ť�� �������?
			} else {

				if (!newCustomerQueue.isEmpty()) {
					for (int i = 0; i < newCustomerQueue.size(); i++) {

						queue.add(newCustomerQueue.poll());

					}
				}

			}

		}

		System.out.println(sb);

	}

}
