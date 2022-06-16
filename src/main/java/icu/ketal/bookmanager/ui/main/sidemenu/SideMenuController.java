package icu.ketal.bookmanager.ui.main.sidemenu;

import com.jfoenix.controls.JFXListView;
import icu.ketal.bookmanager.ui.main.bookInfo.BookInfoController;
import icu.ketal.bookmanager.ui.main.borrow.BorrowController;
import icu.ketal.bookmanager.ui.main.category.CategoryController;
import icu.ketal.bookmanager.ui.main.readerInfo.ReaderInfoController;
import icu.ketal.bookmanager.ui.main.system.SystemController;
import icu.ketal.bookmanager.util.UIHelper;
import io.datafx.controller.ViewController;
import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.action.ActionTrigger;
import io.datafx.controller.flow.context.FXMLViewFlowContext;
import io.datafx.controller.flow.context.ViewFlowContext;
import io.datafx.controller.util.VetoException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;

import javax.annotation.PostConstruct;
import java.util.Objects;

@ViewController(value = "/fxml/SideMenu.fxml")
public class SideMenuController {
    @FXMLViewFlowContext
    private ViewFlowContext context;
    @FXML
    @ActionTrigger("category")
    private Label category;
    @FXML
    @ActionTrigger("bookInfo")
    private Label bookInfo;
    @FXML
    @ActionTrigger("readerInfo")
    private Label readerInfo;
    @FXML
    @ActionTrigger("borrow")
    private Label borrow;
    @FXML
    @ActionTrigger("system")
    private Label system;
    @FXML
    private JFXListView<Label> sideList;

    @PostConstruct
    public void init() throws Exception {
        Objects.requireNonNull(context, "context");
        FlowHandler contentFlowHandler = (FlowHandler) context.getRegisteredObject("ContentFlowHandler");
        sideList.propagateMouseEventsToParent();
        sideList.getSelectionModel().selectedItemProperty().addListener((o, oldVal, newVal) -> {
            new Thread(() -> {
                Platform.runLater(() -> {
                    if (newVal != null) {
                        try {
                            contentFlowHandler.handle(newVal.getId());
                        } catch (VetoException exc) {
                            exc.printStackTrace();
                        } catch (FlowException exc) {
                            exc.printStackTrace();
                        }
                    }
                });
            }).start();
        });
        Flow contentFlow = (Flow) context.getRegisteredObject("ContentFlow");
        bindNodeToController(category, CategoryController.class, contentFlow, contentFlowHandler);
        bindNodeToController(bookInfo, BookInfoController.class, contentFlow, contentFlowHandler);
        bindNodeToController(readerInfo, ReaderInfoController.class, contentFlow, contentFlowHandler);
        bindNodeToController(borrow, BorrowController.class, contentFlow, contentFlowHandler);
        bindNodeToController(system, SystemController.class, contentFlow, contentFlowHandler);

        category.setGraphic(UIHelper.getSVG("folder-open", 16));
        bookInfo.setGraphic(UIHelper.getSVG("book", 16));
        readerInfo.setGraphic(UIHelper.getSVG("user", 16));
        borrow.setGraphic(UIHelper.getSVG("barcode", 16));
        system.setGraphic(UIHelper.getSVG("linux", 16));
    }

    private void bindNodeToController(Node node, Class<?> controllerClass, Flow flow, FlowHandler flowHandler) {
        flow.withGlobalLink(node.getId(), controllerClass);
    }
}
