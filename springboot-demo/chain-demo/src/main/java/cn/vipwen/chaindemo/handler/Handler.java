package cn.vipwen.chaindemo.handler;

/**
 * 责任链处理超类
 *
 * @author wls
 */
public abstract class Handler<T> {
    protected Handler<T> next;

    private void next(Handler<T> next) {
        this.next = next;
    }

    /**
     * 具体处理方法
     *
     * @param t 处理的对象
     */
    public abstract void doHandler(T t);

    public static class Builder<T> {
        private Handler<T> head;
        private Handler<T> tail;

        public Builder<T> addHandler(Handler<T> handler) {
            if (this.head == null) {
                this.head = this.tail = handler;
                return this;
            }
            this.tail.next(handler);
            this.tail = handler;
            return this;
        }

        public Handler<T> build() {
            return this.head;
        }
    }
}
