using System;
using System.Data;
using System.Windows.Forms;
using System.Data.SqlClient;
using System.Configuration;
using System.Collections.Generic;

namespace Assignment1
{
    public partial class Form1 : Form
    {
        private string connectionString = ConfigurationManager.ConnectionStrings["connectionString"].ConnectionString;
        private SqlConnection connection = null;
        private TextBox[] textBoxes;
        private Label[] labels;
        private List<string> childColumnList;


        public Form1()
        {
            InitializeComponent();
            initializeAddUpdatePanel();
        }

        private void connect() {
            try
            {
                connection = new SqlConnection(connectionString);
                connection.Open();
            }
            catch (SqlException e)
            {
                MessageBox.Show(e.Message, "Error connecting to the database", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void disconnect() {
            connection.Close();
        }

        private void initializeAddUpdatePanel() {
            connect();
            flowLayoutPanelAddingUpdatingAChildBoxes.Refresh();
            childColumnList = new List<string>(ConfigurationSettings.AppSettings["childColumnNames"].Split(','));
            textBoxes = new TextBox[childColumnList.Count];
            labels = new Label[childColumnList.Count];
            

            for (int i = 0; i < childColumnList.Count; i++)
            {
                textBoxes[i] = new TextBox();
                labels[i] = new Label();
                labels[i].Text = childColumnList[i];
            }

            for (int i = 0; i < childColumnList.Count; i++)
            {
                flowLayoutPanelAddingUpdatingAChildBoxes.Controls.Add(labels[i]);
                flowLayoutPanelAddingUpdatingAChildBoxes.Controls.Add(textBoxes[i]);
                
            }
            disconnect();

        }



        
        private void buttonDisplayParentTable_Click(object sender, EventArgs e)
        {
            connect();
            DataSet dataSet = new DataSet();
            SqlDataAdapter dataAdapter = new SqlDataAdapter(ConfigurationSettings.AppSettings["selectParentRows"], connection);
            dataAdapter.Fill(dataSet, ConfigurationSettings.AppSettings["parentTable"]);
            dataGridViewParent.DataSource = dataSet.Tables[ConfigurationSettings.AppSettings["parentTable"]];
            disconnect();
        }
        
        
        

        private void buttonDisplayChildRowsForSelectedParent_Click(object sender, EventArgs e)
        {
            connect();
            dataGridViewChildren.Refresh();
            int selectedRow = dataGridViewParent.CurrentCell.RowIndex;
            DataGridViewRow row = dataGridViewParent.Rows[selectedRow];
            string selectedId = Convert.ToString(row.Cells[ConfigurationSettings.AppSettings["parentTableID"]].Value);
            DataSet dataSet = new DataSet();
            SqlDataAdapter dataAdapter = new SqlDataAdapter(ConfigurationSettings.AppSettings["selectChildRowsForSelectedParent"]+selectedId, connection);
            dataAdapter.Fill(dataSet, ConfigurationSettings.AppSettings["childTable"]);
            dataGridViewChildren.DataSource = dataSet.Tables[ConfigurationSettings.AppSettings["childTable"]];
            disconnect();
        }

        

        private void buttonAddChild_Click(object sender, EventArgs e)
        {
            connect();
            try
            {
                int selectedRow = dataGridViewParent.CurrentCell.RowIndex;
                DataGridViewRow row = dataGridViewParent.Rows[selectedRow];
                string selectedId = Convert.ToString(row.Cells[ConfigurationSettings.AppSettings["parentTableID"]].Value);

                SqlDataAdapter addingDataAdapter = new SqlDataAdapter();

                SqlCommand cmd = new SqlCommand(ConfigurationSettings.AppSettings["insertChildRow"], connection);
                for (int i = 0; i < childColumnList.Count; i++)
                {
                    cmd.Parameters.AddWithValue("@" + childColumnList[i], textBoxes[i].Text);
                }
                cmd.Parameters.AddWithValue("@" + ConfigurationSettings.AppSettings["parentTableID"], selectedId);

                addingDataAdapter.InsertCommand = cmd;
                addingDataAdapter.InsertCommand.ExecuteNonQuery();
                disconnect();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message.ToString());
                disconnect();
            }
            

        }

        private void buttonUpdateChild_Click(object sender, EventArgs e)
        {
            connect();
            try {
                int selectedRow = dataGridViewChildren.CurrentCell.RowIndex;
                DataGridViewRow row = dataGridViewChildren.Rows[selectedRow];
                string selectedId = Convert.ToString(row.Cells[ConfigurationSettings.AppSettings["childTableID"]].Value);

            
                SqlDataAdapter updatingDataAdapter = new SqlDataAdapter();

                SqlCommand cmd = new SqlCommand(ConfigurationSettings.AppSettings["updateChildRow"] + selectedId, connection);
                for (int i = 0; i < childColumnList.Count; i++)
                {
                    cmd.Parameters.AddWithValue("@" + childColumnList[i], textBoxes[i].Text);
                }
            
                updatingDataAdapter.UpdateCommand = cmd;
                updatingDataAdapter.UpdateCommand.ExecuteNonQuery();
                disconnect();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message.ToString());
                disconnect();
            }


        }

        private void buttonRemoveChild_Click(object sender, EventArgs e)
        {
            connect();
            try {
                int selectedRow = dataGridViewChildren.CurrentCell.RowIndex;
                DataGridViewRow row = dataGridViewChildren.Rows[selectedRow];
                string selectedId = Convert.ToString(row.Cells[ConfigurationSettings.AppSettings["childTableID"]].Value);
           
                SqlDataAdapter deletingDataAdapter = new SqlDataAdapter();

                SqlCommand cmd = new SqlCommand(ConfigurationSettings.AppSettings["deleteChildRow"] + selectedId, connection);

                deletingDataAdapter.DeleteCommand = cmd;
                deletingDataAdapter.DeleteCommand.ExecuteNonQuery();
                disconnect();
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message.ToString());
                disconnect();
            }

        }


    }
}
