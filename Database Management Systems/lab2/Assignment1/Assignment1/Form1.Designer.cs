using System.Windows.Forms;

namespace Assignment1
{
    public partial class Form1 : Form
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.movie_Rental_DatabaseDataSet = new Assignment1.Movie_Rental_DatabaseDataSet();
            this.employeesBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.employeesTableAdapter = new Assignment1.Movie_Rental_DatabaseDataSetTableAdapters.EmployeesTableAdapter();
            this.tableAdapterManager = new Assignment1.Movie_Rental_DatabaseDataSetTableAdapters.TableAdapterManager();
            this.managersTableAdapter = new Assignment1.Movie_Rental_DatabaseDataSetTableAdapters.ManagersTableAdapter();
            this.employeesBindingNavigator = new System.Windows.Forms.BindingNavigator(this.components);
            this.bindingNavigatorAddNewItem = new System.Windows.Forms.ToolStripButton();
            this.bindingNavigatorCountItem = new System.Windows.Forms.ToolStripLabel();
            this.bindingNavigatorDeleteItem = new System.Windows.Forms.ToolStripButton();
            this.bindingNavigatorMoveFirstItem = new System.Windows.Forms.ToolStripButton();
            this.bindingNavigatorMovePreviousItem = new System.Windows.Forms.ToolStripButton();
            this.bindingNavigatorSeparator = new System.Windows.Forms.ToolStripSeparator();
            this.bindingNavigatorPositionItem = new System.Windows.Forms.ToolStripTextBox();
            this.bindingNavigatorSeparator1 = new System.Windows.Forms.ToolStripSeparator();
            this.bindingNavigatorMoveNextItem = new System.Windows.Forms.ToolStripButton();
            this.bindingNavigatorMoveLastItem = new System.Windows.Forms.ToolStripButton();
            this.bindingNavigatorSeparator2 = new System.Windows.Forms.ToolStripSeparator();
            this.employeesBindingNavigatorSaveItem = new System.Windows.Forms.ToolStripButton();
            this.managersBindingSource = new System.Windows.Forms.BindingSource(this.components);
            this.movie_Rental_DatabaseDataSet1 = new Assignment1.Movie_Rental_DatabaseDataSet();
            this.dataGridViewChildren = new System.Windows.Forms.DataGridView();
            this.dataGridViewParent = new System.Windows.Forms.DataGridView();
            this.buttonDisplayParentTable = new System.Windows.Forms.Button();
            this.buttonDisplayChildRowsForSelectedParent = new System.Windows.Forms.Button();
            this.buttonUpdateChild = new System.Windows.Forms.Button();
            this.buttonAddChild = new System.Windows.Forms.Button();
            this.buttonRemoveChild = new System.Windows.Forms.Button();
            this.Parent = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.flowLayoutPanelAddingUpdatingAChildBoxes = new System.Windows.Forms.FlowLayoutPanel();
            ((System.ComponentModel.ISupportInitialize)(this.movie_Rental_DatabaseDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.employeesBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.employeesBindingNavigator)).BeginInit();
            this.employeesBindingNavigator.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.managersBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.movie_Rental_DatabaseDataSet1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewChildren)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewParent)).BeginInit();
            this.SuspendLayout();
            // 
            // movie_Rental_DatabaseDataSet
            // 
            this.movie_Rental_DatabaseDataSet.DataSetName = "Movie_Rental_DatabaseDataSet";
            this.movie_Rental_DatabaseDataSet.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // employeesBindingSource
            // 
            this.employeesBindingSource.DataMember = "Employees";
            this.employeesBindingSource.DataSource = this.movie_Rental_DatabaseDataSet;
            // 
            // employeesTableAdapter
            // 
            this.employeesTableAdapter.ClearBeforeFill = true;
            // 
            // tableAdapterManager
            // 
            this.tableAdapterManager.BackupDataSetBeforeUpdate = false;
            this.tableAdapterManager.EmployeesTableAdapter = this.employeesTableAdapter;
            this.tableAdapterManager.ManagersTableAdapter = this.managersTableAdapter;
            this.tableAdapterManager.UpdateOrder = Assignment1.Movie_Rental_DatabaseDataSetTableAdapters.TableAdapterManager.UpdateOrderOption.InsertUpdateDelete;
            // 
            // managersTableAdapter
            // 
            this.managersTableAdapter.ClearBeforeFill = true;
            // 
            // employeesBindingNavigator
            // 
            this.employeesBindingNavigator.AddNewItem = this.bindingNavigatorAddNewItem;
            this.employeesBindingNavigator.BindingSource = this.employeesBindingSource;
            this.employeesBindingNavigator.CountItem = this.bindingNavigatorCountItem;
            this.employeesBindingNavigator.DeleteItem = this.bindingNavigatorDeleteItem;
            this.employeesBindingNavigator.ImageScalingSize = new System.Drawing.Size(20, 20);
            this.employeesBindingNavigator.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.bindingNavigatorMoveFirstItem,
            this.bindingNavigatorMovePreviousItem,
            this.bindingNavigatorSeparator,
            this.bindingNavigatorPositionItem,
            this.bindingNavigatorCountItem,
            this.bindingNavigatorSeparator1,
            this.bindingNavigatorMoveNextItem,
            this.bindingNavigatorMoveLastItem,
            this.bindingNavigatorSeparator2,
            this.bindingNavigatorAddNewItem,
            this.bindingNavigatorDeleteItem,
            this.employeesBindingNavigatorSaveItem});
            this.employeesBindingNavigator.Location = new System.Drawing.Point(0, 0);
            this.employeesBindingNavigator.MoveFirstItem = this.bindingNavigatorMoveFirstItem;
            this.employeesBindingNavigator.MoveLastItem = this.bindingNavigatorMoveLastItem;
            this.employeesBindingNavigator.MoveNextItem = this.bindingNavigatorMoveNextItem;
            this.employeesBindingNavigator.MovePreviousItem = this.bindingNavigatorMovePreviousItem;
            this.employeesBindingNavigator.Name = "employeesBindingNavigator";
            this.employeesBindingNavigator.PositionItem = this.bindingNavigatorPositionItem;
            this.employeesBindingNavigator.Size = new System.Drawing.Size(1337, 27);
            this.employeesBindingNavigator.TabIndex = 0;
            this.employeesBindingNavigator.Text = "bindingNavigator1";
            // 
            // bindingNavigatorAddNewItem
            // 
            this.bindingNavigatorAddNewItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bindingNavigatorAddNewItem.Image = ((System.Drawing.Image)(resources.GetObject("bindingNavigatorAddNewItem.Image")));
            this.bindingNavigatorAddNewItem.Name = "bindingNavigatorAddNewItem";
            this.bindingNavigatorAddNewItem.RightToLeftAutoMirrorImage = true;
            this.bindingNavigatorAddNewItem.Size = new System.Drawing.Size(24, 24);
            this.bindingNavigatorAddNewItem.Text = "Add new";
            // 
            // bindingNavigatorCountItem
            // 
            this.bindingNavigatorCountItem.Name = "bindingNavigatorCountItem";
            this.bindingNavigatorCountItem.Size = new System.Drawing.Size(45, 24);
            this.bindingNavigatorCountItem.Text = "of {0}";
            this.bindingNavigatorCountItem.ToolTipText = "Total number of items";
            // 
            // bindingNavigatorDeleteItem
            // 
            this.bindingNavigatorDeleteItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bindingNavigatorDeleteItem.Image = ((System.Drawing.Image)(resources.GetObject("bindingNavigatorDeleteItem.Image")));
            this.bindingNavigatorDeleteItem.Name = "bindingNavigatorDeleteItem";
            this.bindingNavigatorDeleteItem.RightToLeftAutoMirrorImage = true;
            this.bindingNavigatorDeleteItem.Size = new System.Drawing.Size(24, 24);
            this.bindingNavigatorDeleteItem.Text = "Delete";
            // 
            // bindingNavigatorMoveFirstItem
            // 
            this.bindingNavigatorMoveFirstItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bindingNavigatorMoveFirstItem.Image = ((System.Drawing.Image)(resources.GetObject("bindingNavigatorMoveFirstItem.Image")));
            this.bindingNavigatorMoveFirstItem.Name = "bindingNavigatorMoveFirstItem";
            this.bindingNavigatorMoveFirstItem.RightToLeftAutoMirrorImage = true;
            this.bindingNavigatorMoveFirstItem.Size = new System.Drawing.Size(24, 24);
            this.bindingNavigatorMoveFirstItem.Text = "Move first";
            // 
            // bindingNavigatorMovePreviousItem
            // 
            this.bindingNavigatorMovePreviousItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bindingNavigatorMovePreviousItem.Image = ((System.Drawing.Image)(resources.GetObject("bindingNavigatorMovePreviousItem.Image")));
            this.bindingNavigatorMovePreviousItem.Name = "bindingNavigatorMovePreviousItem";
            this.bindingNavigatorMovePreviousItem.RightToLeftAutoMirrorImage = true;
            this.bindingNavigatorMovePreviousItem.Size = new System.Drawing.Size(24, 24);
            this.bindingNavigatorMovePreviousItem.Text = "Move previous";
            // 
            // bindingNavigatorSeparator
            // 
            this.bindingNavigatorSeparator.Name = "bindingNavigatorSeparator";
            this.bindingNavigatorSeparator.Size = new System.Drawing.Size(6, 27);
            // 
            // bindingNavigatorPositionItem
            // 
            this.bindingNavigatorPositionItem.AccessibleName = "Position";
            this.bindingNavigatorPositionItem.AutoSize = false;
            this.bindingNavigatorPositionItem.Name = "bindingNavigatorPositionItem";
            this.bindingNavigatorPositionItem.Size = new System.Drawing.Size(50, 27);
            this.bindingNavigatorPositionItem.Text = "0";
            this.bindingNavigatorPositionItem.ToolTipText = "Current position";
            // 
            // bindingNavigatorSeparator1
            // 
            this.bindingNavigatorSeparator1.Name = "bindingNavigatorSeparator1";
            this.bindingNavigatorSeparator1.Size = new System.Drawing.Size(6, 27);
            // 
            // bindingNavigatorMoveNextItem
            // 
            this.bindingNavigatorMoveNextItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bindingNavigatorMoveNextItem.Image = ((System.Drawing.Image)(resources.GetObject("bindingNavigatorMoveNextItem.Image")));
            this.bindingNavigatorMoveNextItem.Name = "bindingNavigatorMoveNextItem";
            this.bindingNavigatorMoveNextItem.RightToLeftAutoMirrorImage = true;
            this.bindingNavigatorMoveNextItem.Size = new System.Drawing.Size(24, 24);
            this.bindingNavigatorMoveNextItem.Text = "Move next";
            // 
            // bindingNavigatorMoveLastItem
            // 
            this.bindingNavigatorMoveLastItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.bindingNavigatorMoveLastItem.Image = ((System.Drawing.Image)(resources.GetObject("bindingNavigatorMoveLastItem.Image")));
            this.bindingNavigatorMoveLastItem.Name = "bindingNavigatorMoveLastItem";
            this.bindingNavigatorMoveLastItem.RightToLeftAutoMirrorImage = true;
            this.bindingNavigatorMoveLastItem.Size = new System.Drawing.Size(24, 24);
            this.bindingNavigatorMoveLastItem.Text = "Move last";
            // 
            // bindingNavigatorSeparator2
            // 
            this.bindingNavigatorSeparator2.Name = "bindingNavigatorSeparator2";
            this.bindingNavigatorSeparator2.Size = new System.Drawing.Size(6, 27);
            // 
            // employeesBindingNavigatorSaveItem
            // 
            this.employeesBindingNavigatorSaveItem.DisplayStyle = System.Windows.Forms.ToolStripItemDisplayStyle.Image;
            this.employeesBindingNavigatorSaveItem.Image = ((System.Drawing.Image)(resources.GetObject("employeesBindingNavigatorSaveItem.Image")));
            this.employeesBindingNavigatorSaveItem.Name = "employeesBindingNavigatorSaveItem";
            this.employeesBindingNavigatorSaveItem.Size = new System.Drawing.Size(24, 24);
            this.employeesBindingNavigatorSaveItem.Text = "Save Data";
            // 
            // managersBindingSource
            // 
            this.managersBindingSource.DataMember = "Managers";
            this.managersBindingSource.DataSource = this.movie_Rental_DatabaseDataSet;
            // 
            // movie_Rental_DatabaseDataSet1
            // 
            this.movie_Rental_DatabaseDataSet1.DataSetName = "Movie_Rental_DatabaseDataSet";
            this.movie_Rental_DatabaseDataSet1.SchemaSerializationMode = System.Data.SchemaSerializationMode.IncludeSchema;
            // 
            // dataGridViewChildren
            // 
            this.dataGridViewChildren.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewChildren.Location = new System.Drawing.Point(0, 30);
            this.dataGridViewChildren.Name = "dataGridViewChildren";
            this.dataGridViewChildren.RowTemplate.Height = 24;
            this.dataGridViewChildren.Size = new System.Drawing.Size(690, 150);
            this.dataGridViewChildren.TabIndex = 1;
            // 
            // dataGridViewParent
            // 
            this.dataGridViewParent.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewParent.Location = new System.Drawing.Point(696, 30);
            this.dataGridViewParent.Name = "dataGridViewParent";
            this.dataGridViewParent.RowTemplate.Height = 24;
            this.dataGridViewParent.Size = new System.Drawing.Size(641, 150);
            this.dataGridViewParent.TabIndex = 2;
            // 
            // buttonDisplayParentTable
            // 
            this.buttonDisplayParentTable.Location = new System.Drawing.Point(12, 285);
            this.buttonDisplayParentTable.Name = "buttonDisplayParentTable";
            this.buttonDisplayParentTable.Size = new System.Drawing.Size(176, 59);
            this.buttonDisplayParentTable.TabIndex = 3;
            this.buttonDisplayParentTable.Text = "Display parent table";
            this.buttonDisplayParentTable.UseVisualStyleBackColor = true;
            this.buttonDisplayParentTable.Click += new System.EventHandler(this.buttonDisplayParentTable_Click);
            // 
            // buttonDisplayChildRowsForSelectedParent
            // 
            this.buttonDisplayChildRowsForSelectedParent.Location = new System.Drawing.Point(12, 350);
            this.buttonDisplayChildRowsForSelectedParent.Name = "buttonDisplayChildRowsForSelectedParent";
            this.buttonDisplayChildRowsForSelectedParent.Size = new System.Drawing.Size(176, 89);
            this.buttonDisplayChildRowsForSelectedParent.TabIndex = 4;
            this.buttonDisplayChildRowsForSelectedParent.Text = "Display child rows for the parent row selected";
            this.buttonDisplayChildRowsForSelectedParent.UseVisualStyleBackColor = true;
            this.buttonDisplayChildRowsForSelectedParent.Click += new System.EventHandler(this.buttonDisplayChildRowsForSelectedParent_Click);
            // 
            // buttonUpdateChild
            // 
            this.buttonUpdateChild.Location = new System.Drawing.Point(864, 377);
            this.buttonUpdateChild.Name = "buttonUpdateChild";
            this.buttonUpdateChild.Size = new System.Drawing.Size(190, 34);
            this.buttonUpdateChild.TabIndex = 9;
            this.buttonUpdateChild.Text = "Update child";
            this.buttonUpdateChild.UseVisualStyleBackColor = true;
            this.buttonUpdateChild.Click += new System.EventHandler(this.buttonUpdateChild_Click);
            // 
            // buttonAddChild
            // 
            this.buttonAddChild.Location = new System.Drawing.Point(864, 319);
            this.buttonAddChild.Name = "buttonAddChild";
            this.buttonAddChild.Size = new System.Drawing.Size(190, 36);
            this.buttonAddChild.TabIndex = 8;
            this.buttonAddChild.Text = "Add child";
            this.buttonAddChild.UseVisualStyleBackColor = true;
            this.buttonAddChild.Click += new System.EventHandler(this.buttonAddChild_Click);
            // 
            // buttonRemoveChild
            // 
            this.buttonRemoveChild.Location = new System.Drawing.Point(864, 224);
            this.buttonRemoveChild.Name = "buttonRemoveChild";
            this.buttonRemoveChild.Size = new System.Drawing.Size(190, 69);
            this.buttonRemoveChild.TabIndex = 6;
            this.buttonRemoveChild.Text = "Remove a child row for a selected parent";
            this.buttonRemoveChild.UseVisualStyleBackColor = true;
            this.buttonRemoveChild.Click += new System.EventHandler(this.buttonRemoveChild_Click);
            // 
            // Parent
            // 
            this.Parent.AutoSize = true;
            this.Parent.Location = new System.Drawing.Point(985, 187);
            this.Parent.Name = "Parent";
            this.Parent.Size = new System.Drawing.Size(50, 17);
            this.Parent.TabIndex = 7;
            this.Parent.Text = "Parent";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(276, 186);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(39, 17);
            this.label3.TabIndex = 8;
            this.label3.Text = "Child";
            // 
            // flowLayoutPanelAddingUpdatingAChildBoxes
            // 
            this.flowLayoutPanelAddingUpdatingAChildBoxes.Location = new System.Drawing.Point(349, 207);
            this.flowLayoutPanelAddingUpdatingAChildBoxes.Name = "flowLayoutPanelAddingUpdatingAChildBoxes";
            this.flowLayoutPanelAddingUpdatingAChildBoxes.Size = new System.Drawing.Size(223, 232);
            this.flowLayoutPanelAddingUpdatingAChildBoxes.TabIndex = 10;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1337, 458);
            this.Controls.Add(this.flowLayoutPanelAddingUpdatingAChildBoxes);
            this.Controls.Add(this.buttonUpdateChild);
            this.Controls.Add(this.buttonAddChild);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.Parent);
            this.Controls.Add(this.buttonRemoveChild);
            this.Controls.Add(this.buttonDisplayChildRowsForSelectedParent);
            this.Controls.Add(this.buttonDisplayParentTable);
            this.Controls.Add(this.dataGridViewParent);
            this.Controls.Add(this.dataGridViewChildren);
            this.Controls.Add(this.employeesBindingNavigator);
            this.Name = "Form1";
            this.Text = "Form1";
            ((System.ComponentModel.ISupportInitialize)(this.movie_Rental_DatabaseDataSet)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.employeesBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.employeesBindingNavigator)).EndInit();
            this.employeesBindingNavigator.ResumeLayout(false);
            this.employeesBindingNavigator.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.managersBindingSource)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.movie_Rental_DatabaseDataSet1)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewChildren)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewParent)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion
        
        private Movie_Rental_DatabaseDataSet movie_Rental_DatabaseDataSet;
        private System.Windows.Forms.BindingSource employeesBindingSource;
        private Movie_Rental_DatabaseDataSetTableAdapters.EmployeesTableAdapter employeesTableAdapter;
        private Movie_Rental_DatabaseDataSetTableAdapters.TableAdapterManager tableAdapterManager;
        private System.Windows.Forms.BindingNavigator employeesBindingNavigator;
        private System.Windows.Forms.ToolStripButton bindingNavigatorAddNewItem;
        private System.Windows.Forms.ToolStripLabel bindingNavigatorCountItem;
        private System.Windows.Forms.ToolStripButton bindingNavigatorDeleteItem;
        private System.Windows.Forms.ToolStripButton bindingNavigatorMoveFirstItem;
        private System.Windows.Forms.ToolStripButton bindingNavigatorMovePreviousItem;
        private System.Windows.Forms.ToolStripSeparator bindingNavigatorSeparator;
        private System.Windows.Forms.ToolStripTextBox bindingNavigatorPositionItem;
        private System.Windows.Forms.ToolStripSeparator bindingNavigatorSeparator1;
        private System.Windows.Forms.ToolStripButton bindingNavigatorMoveNextItem;
        private System.Windows.Forms.ToolStripButton bindingNavigatorMoveLastItem;
        private System.Windows.Forms.ToolStripSeparator bindingNavigatorSeparator2;
        private System.Windows.Forms.ToolStripButton employeesBindingNavigatorSaveItem;
        private Movie_Rental_DatabaseDataSetTableAdapters.ManagersTableAdapter managersTableAdapter;
        private System.Windows.Forms.BindingSource managersBindingSource;
        private Movie_Rental_DatabaseDataSet movie_Rental_DatabaseDataSet1;
        private DataGridView dataGridViewChildren;
        private DataGridView dataGridViewParent;
        private Button buttonDisplayParentTable;
        private Button buttonDisplayChildRowsForSelectedParent;
        private Button buttonAddChild;
        private Button buttonUpdateChild;
        private Button buttonRemoveChild;
        private Label Parent;
        private Label label3;
        private FlowLayoutPanel flowLayoutPanelAddingUpdatingAChildBoxes;
    }
}

