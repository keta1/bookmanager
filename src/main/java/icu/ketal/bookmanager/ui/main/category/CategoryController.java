package icu.ketal.bookmanager.ui.main.category;

import com.jfoenix.controls.*;
import com.jfoenix.controls.cells.editors.DoubleTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.IntegerTextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.GenericEditableTreeTableCell;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import icu.ketal.bookmanager.dao.CategoryDao;
import icu.ketal.bookmanager.dao.impl.CategoryDaoImpl;
import icu.ketal.bookmanager.util.DialogBuilder;
import icu.ketal.bookmanager.util.UIHelper;
import io.datafx.controller.ViewController;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellEditEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.function.Function;

@ViewController(value = "/fxml/ui/category/category.fxml")
public class CategoryController {
    @FXML
    JFXNodesList nodesList;
    @FXML
    JFXButton newButton;
    @FXML
    JFXButton submit;
    @FXML
    JFXButton reload;
    @FXML
    private JFXTreeTableView<Category> treeTableView;
    @FXML
    private JFXTreeTableColumn<Category, Integer> id;
    @FXML
    private JFXTreeTableColumn<Category, String> name;
    @FXML
    private JFXTreeTableColumn<Category, Integer> daysToReturn;
    @FXML
    private JFXTreeTableColumn<Category, Double> finePerDay;
    private final CategoryDao dao = new CategoryDaoImpl();
    private ObservableList<Category> dummyData;

    /**
     * init fxml when loaded.
     */
    @PostConstruct
    public void init() throws Exception {
        newButton.setGraphic(UIHelper.getSVG("file-o", 24));
        submit.setGraphic(UIHelper.getSVG("arrow-up", 24));
        reload.setGraphic(UIHelper.getSVG("repeat, rotate-right", 24));
        setupEditableTableView();
    }

    private <T> void setupCellValueFactory(JFXTreeTableColumn<Category, T> column, Function<Category, ObservableValue<T>> mapper) {
        column.setCellValueFactory((TreeTableColumn.CellDataFeatures<Category, T> param) -> {
            if (column.validateValue(param)) {
                return mapper.apply(param.getValue().getValue());
            } else {
                return column.getComputedValue(param);
            }
        });
    }

    private void setupEditableTableView() {
        setupCellValueFactory(id, p -> p.id.asObject());
        setupCellValueFactory(name, Category::nameProperty);
        setupCellValueFactory(daysToReturn, p -> p.daysToReturn.asObject());
        setupCellValueFactory(finePerDay, p -> p.finePerDay.asObject());

        // add editors
        name.setCellFactory((TreeTableColumn<Category, String> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new TextFieldEditorBuilder());
        });
        name.setOnEditCommit((CellEditEvent<Category, String> t) -> {
            var data = t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue();
            data.name.set(t.getNewValue());
        });
        daysToReturn.setCellFactory((TreeTableColumn<Category, Integer> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new IntegerTextFieldEditorBuilder());
        });
        daysToReturn.setOnEditCommit((CellEditEvent<Category, Integer> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().daysToReturn.set(t.getNewValue());
        });
        finePerDay.setCellFactory((TreeTableColumn<Category, Double> param) -> {
            return new GenericEditableTreeTableCell<>(
                    new DoubleTextFieldEditorBuilder());
        });
        finePerDay.setOnEditCommit((CellEditEvent<Category, Double> t) -> {
            t.getTreeTableView()
                    .getTreeItem(t.getTreeTablePosition()
                            .getRow())
                    .getValue().finePerDay.set(t.getNewValue());
        });

        reload();
        treeTableView.setShowRoot(false);
        treeTableView.setEditable(true);
    }

    private ObservableList<Category> selectAll() {
        final ObservableList<Category> dummy = FXCollections.observableArrayList();
        dao.selectAll()
                .stream()
                .map(i -> new Category(i.getId(), i.getName(), i.getDaysToReturn(), i.getFinePerDay()))
                .forEach(dummy::add);
        return dummy;
    }

    private void reload() {
        dummyData = selectAll();
        treeTableView.setRoot(new RecursiveTreeItem<>(dummyData, RecursiveTreeObject::getChildren));
    }

    private void crate(int id) {
        dummyData.add(new Category(id, "null", -1, -1));
        final IntegerProperty currCountProp = treeTableView.currentItemsCountProperty();
        currCountProp.set(currCountProp.get() + 1);
    }

    @FXML
    public void onNewButton() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ui/category/InputDialog.fxml"));
        loader.setController(new InputController());
        VBox view = loader.load();
        var layout = new JFXDialogLayout();
        layout.setBody(view);
        new DialogBuilder<>(newButton.getScene().getWindow())
                .setContent(layout)
                .build()
                .show();
        nodesList.animateList(false);
    }

    @FXML
    public void onSubmit() {
        dummyData.forEach(data -> {
            var cc = new icu.ketal.bookmanager.entry.Category() {{
                setId(data.id.get());
                setName(data.nameProperty().get());
                setDaysToReturn(data.daysToReturn.get());
                setFinePerDay(data.finePerDay.get());
            }};
            dao.replace(cc);
        });
        reload();
        var snackbar = new JFXSnackbar((Pane) nodesList.getScene().getRoot());
        snackbar.setPrefWidth(300);
        snackbar.fireEvent(new JFXSnackbar.SnackbarEvent(
                new JFXSnackbarLayout("修改成功", "", null),
                Duration.millis(3000), null));
        nodesList.animateList(false);
    }

    @FXML
    public void onReload() {
        reload();
        nodesList.animateList(false);
    }

    static final class Category extends RecursiveTreeObject<Category> {
        final SimpleIntegerProperty id;
        final StringProperty name;
        final SimpleIntegerProperty daysToReturn;
        final SimpleDoubleProperty finePerDay;

        Category(int id, String lastName, int age, double finePerDay) {
            this.id = new SimpleIntegerProperty(id);
            this.name = new SimpleStringProperty(lastName);
            this.daysToReturn = new SimpleIntegerProperty(age);
            this.finePerDay = new SimpleDoubleProperty(finePerDay);
        }

        StringProperty nameProperty() {
            return name;
        }
    }

    public final class InputController {
        @FXML
        private JFXTextField input;

        @FXML
        private void onClick() {
            crate(Integer.parseInt(input.getText()));
            input.getScene().getWindow().hide();
        }
    }
}
