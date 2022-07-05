package product;

public interface CartProductListener {
    void onClickIncBtn(int position);
    void onClickDescBtn(int position);
    void onFocusQuanEdt(int position, String value);
}
