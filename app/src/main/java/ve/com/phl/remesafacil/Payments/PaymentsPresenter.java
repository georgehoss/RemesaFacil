package ve.com.phl.remesafacil.Payments;

/**
 * Created by ghoss on 03/09/2018.
 */
public class PaymentsPresenter implements PaymentsContract.Presenter {

    private PaymentsContract.View mView;

    PaymentsPresenter(PaymentsContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void bindView(PaymentsContract.View view) {
        this.mView = view;
    }

    @Override
    public void unbindView() {
        this.mView = null;
    }
}
