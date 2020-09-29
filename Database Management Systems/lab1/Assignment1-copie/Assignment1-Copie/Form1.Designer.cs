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
            this.dataGridViewSchools = new System.Windows.Forms.DataGridView();
            this.dataGridViewPrograms = new System.Windows.Forms.DataGridView();
            this.buttonDisplayAllKidsProgramms = new System.Windows.Forms.Button();
            this.buttonDisplaySchoolsForSelectedProgramm = new System.Windows.Forms.Button();
            this.groupBoxAddingUpdatingASchool = new System.Windows.Forms.GroupBox();
            this.buttonUpdateSchool = new System.Windows.Forms.Button();
            this.buttonAddSchool = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.label1 = new System.Windows.Forms.Label();
            this.textBoxNrOfKids = new System.Windows.Forms.TextBox();
            this.textBoxNameOfSchool = new System.Windows.Forms.TextBox();
            this.buttonRemoveSchool = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.movie_Rental_DatabaseDataSet)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.employeesBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.employeesBindingNavigator)).BeginInit();
            this.employeesBindingNavigator.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.managersBindingSource)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.movie_Rental_DatabaseDataSet1)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewSchools)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewPrograms)).BeginInit();
            this.groupBoxAddingUpdatingASchool.SuspendLayout();
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
            // dataGridViewSchools
            // 
            this.dataGridViewSchools.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewSchools.Location = new System.Drawing.Point(0, 30);
            this.dataGridViewSchools.Name = "dataGridViewSchools";
            this.dataGridViewSchools.RowTemplate.Height = 24;
            this.dataGridViewSchools.Size = new System.Drawing.Size(690, 150);
            this.dataGridViewSchools.TabIndex = 1;
            // 
            // dataGridViewPrograms
            // 
            this.dataGridViewPrograms.ColumnHeadersHeightSizeMode = System.Windows.Forms.DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            this.dataGridViewPrograms.Location = new System.Drawing.Point(696, 30);
            this.dataGridViewPrograms.Name = "dataGridViewPrograms";
            this.dataGridViewPrograms.RowTemplate.Height = 24;
            this.dataGridViewPrograms.Size = new System.Drawing.Size(641, 150);
            this.dataGridViewPrograms.TabIndex = 2;
            // 
            // buttonDisplayAllKidsProgramms
            // 
            this.buttonDisplayAllKidsProgramms.Location = new System.Drawing.Point(12, 216);
            this.buttonDisplayAllKidsProgramms.Name = "buttonDisplayAllKidsProgramms";
            this.buttonDisplayAllKidsProgramms.Size = new System.Drawing.Size(176, 59);
            this.buttonDisplayAllKidsProgramms.TabIndex = 3;
            this.buttonDisplayAllKidsProgramms.Text = "Display all kids programms";
            this.buttonDisplayAllKidsProgramms.UseVisualStyleBackColor = true;
            this.buttonDisplayAllKidsProgramms.Click += new System.EventHandler(this.buttonDisplayAllKidsProgramms_Click);
            // 
            // buttonDisplaySchoolsForSelectedProgramm
            // 
            this.buttonDisplaySchoolsForSelectedProgramm.Location = new System.Drawing.Point(12, 288);
            this.buttonDisplaySchoolsForSelectedProgramm.Name = "buttonDisplaySchoolsForSelectedProgramm";
            this.buttonDisplaySchoolsForSelectedProgramm.Size = new System.Drawing.Size(176, 89);
            this.buttonDisplaySchoolsForSelectedProgramm.TabIndex = 4;
            this.buttonDisplaySchoolsForSelectedProgramm.Text = "Display all schools that take part in a programm";
            this.buttonDisplaySchoolsForSelectedProgramm.UseVisualStyleBackColor = true;
            this.buttonDisplaySchoolsForSelectedProgramm.Click += new System.EventHandler(this.buttonDisplayAllSchoolsForSelectedProgramm_Click);
            // 
            // groupBoxAddingUpdatingASchool
            // 
            this.groupBoxAddingUpdatingASchool.Controls.Add(this.buttonUpdateSchool);
            this.groupBoxAddingUpdatingASchool.Controls.Add(this.buttonAddSchool);
            this.groupBoxAddingUpdatingASchool.Controls.Add(this.label2);
            this.groupBoxAddingUpdatingASchool.Controls.Add(this.label1);
            this.groupBoxAddingUpdatingASchool.Controls.Add(this.textBoxNrOfKids);
            this.groupBoxAddingUpdatingASchool.Controls.Add(this.textBoxNameOfSchool);
            this.groupBoxAddingUpdatingASchool.Location = new System.Drawing.Point(349, 202);
            this.groupBoxAddingUpdatingASchool.Name = "groupBoxAddingUpdatingASchool";
            this.groupBoxAddingUpdatingASchool.Size = new System.Drawing.Size(379, 244);
            this.groupBoxAddingUpdatingASchool.TabIndex = 5;
            this.groupBoxAddingUpdatingASchool.TabStop = false;
            this.groupBoxAddingUpdatingASchool.Text = "Adding/Updating a school for a selected programm";
            // 
            // buttonUpdateSchool
            // 
            this.buttonUpdateSchool.Location = new System.Drawing.Point(35, 190);
            this.buttonUpdateSchool.Name = "buttonUpdateSchool";
            this.buttonUpdateSchool.Size = new System.Drawing.Size(270, 34);
            this.buttonUpdateSchool.TabIndex = 9;
            this.buttonUpdateSchool.Text = "Update School";
            this.buttonUpdateSchool.UseVisualStyleBackColor = true;
            this.buttonUpdateSchool.Click += new System.EventHandler(this.buttonUpdateSchool_Click);
            // 
            // buttonAddSchool
            // 
            this.buttonAddSchool.Location = new System.Drawing.Point(35, 148);
            this.buttonAddSchool.Name = "buttonAddSchool";
            this.buttonAddSchool.Size = new System.Drawing.Size(270, 36);
            this.buttonAddSchool.TabIndex = 8;
            this.buttonAddSchool.Text = "Add school";
            this.buttonAddSchool.UseVisualStyleBackColor = true;
            this.buttonAddSchool.Click += new System.EventHandler(this.buttonAddSchool_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(12, 86);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(69, 17);
            this.label2.TabIndex = 5;
            this.label2.Text = "NrOfKids:";
            
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(32, 54);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(49, 17);
            this.label1.TabIndex = 4;
            this.label1.Text = "Name:";
            // 
            // textBoxNrOfKids
            // 
            this.textBoxNrOfKids.Location = new System.Drawing.Point(101, 86);
            this.textBoxNrOfKids.Name = "textBoxNrOfKids";
            this.textBoxNrOfKids.Size = new System.Drawing.Size(204, 22);
            this.textBoxNrOfKids.TabIndex = 1;
            
            // 
            // textBoxNameOfSchool
            // 
            this.textBoxNameOfSchool.Location = new System.Drawing.Point(101, 54);
            this.textBoxNameOfSchool.Name = "textBoxNameOfSchool";
            this.textBoxNameOfSchool.Size = new System.Drawing.Size(204, 22);
            this.textBoxNameOfSchool.TabIndex = 0;
            
            // 
            // buttonRemoveSchool
            // 
            this.buttonRemoveSchool.Location = new System.Drawing.Point(864, 224);
            this.buttonRemoveSchool.Name = "buttonRemoveSchool";
            this.buttonRemoveSchool.Size = new System.Drawing.Size(190, 69);
            this.buttonRemoveSchool.TabIndex = 6;
            this.buttonRemoveSchool.Text = "Remove a school for a selected programm";
            this.buttonRemoveSchool.UseVisualStyleBackColor = true;
            this.buttonRemoveSchool.Click += new System.EventHandler(this.buttonRemoveSchool_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(1337, 458);
            this.Controls.Add(this.buttonRemoveSchool);
            this.Controls.Add(this.groupBoxAddingUpdatingASchool);
            this.Controls.Add(this.buttonDisplaySchoolsForSelectedProgramm);
            this.Controls.Add(this.buttonDisplayAllKidsProgramms);
            this.Controls.Add(this.dataGridViewPrograms);
            this.Controls.Add(this.dataGridViewSchools);
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
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewSchools)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.dataGridViewPrograms)).EndInit();
            this.groupBoxAddingUpdatingASchool.ResumeLayout(false);
            this.groupBoxAddingUpdatingASchool.PerformLayout();
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
        private DataGridView dataGridViewSchools;
        private DataGridView dataGridViewPrograms;
        private Button buttonDisplayAllKidsProgramms;
        private Button buttonDisplaySchoolsForSelectedProgramm;
        private GroupBox groupBoxAddingUpdatingASchool;
        private Label label1;
        private TextBox textBoxNrOfKids;
        private TextBox textBoxNameOfSchool;
        private Label label2;
        private Button buttonAddSchool;
        private Button buttonUpdateSchool;
        private Button buttonRemoveSchool;
    }
}

