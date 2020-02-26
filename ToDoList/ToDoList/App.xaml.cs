using System;
using System.IO;
using Xamarin.Forms;
using Xamarin.Forms.Xaml;
using ToDoList.Data;

namespace ToDoList
{
    public partial class App : Application
    {
        static TaskDatabase database;

        public static TaskDatabase Database
        {
            get
            {
                if(database == null)
                {
                    database = new TaskDatabase(Path.Combine(Environment.GetFolderPath(Environment.SpecialFolder.LocalApplicationData), "Tasks.db3"));
                }
                return database;
            }
        }

        public static string FolderPath { get; private set; }


        public App()
        {
            InitializeComponent();
            MainPage = new NavigationPage(new TasksPage());
        }

        protected override void OnStart()
        {
        }

        protected override void OnSleep()
        {
        }

        protected override void OnResume()
        {
        }
    }
}
