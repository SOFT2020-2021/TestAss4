package dataLayer.entitites;

public class Transaction extends Transferable {
        Account target;
        long amount;
        long timestamp;

        public Transaction(Account target, long amount, long timestamp) {
            this.target = target;
            this.amount = amount;
            this.timestamp = timestamp;
        }

        public Account getTarget() {
            return target;
        }

        public long getAmount() {
            return amount;
        }

        public long getTimestamp() {
            return timestamp;
        }

}
