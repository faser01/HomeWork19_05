class SumThread extends Thread {
    private int[][] array;
    private int startRow;
    private int endRow;
    private int sum = 0;



    public SumThread(int[][] array, int startRow, int endRow) {
        this.array = array;
        this.startRow = startRow;
        this.endRow = endRow;
    }

    @Override
    public void run() {
        for (int i = startRow; i < endRow; i++) {
            for (int j = 0; j < array[i].length; j++) {
                sum += array[i][j];
            }
            System.out.println("Сумма, рассчитанная в потоке " + Thread.currentThread().getId() +
                    " в строке " + i + ": " + sum);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getSum() {
        return sum;
    }
}

