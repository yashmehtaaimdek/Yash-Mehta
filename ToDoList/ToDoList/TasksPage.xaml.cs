using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using Xamarin.Forms;
using ToDoList.Models;

namespace ToDoList
{
    public partial class TasksPage : ContentPage
    {
        public TasksPage()
        {
            InitializeComponent();
        }

        protected override async void OnAppearing()
        {
            base.OnAppearing();

            listView.ItemsSource = await App.Database.GetNotesAsync();
        }

        async void ToolbarItem_Clicked(System.Object sender, System.EventArgs e)
        {
            await Navigation.PushAsync(new TaskEntryPage
            {
                BindingContext = new Task()
            });
        }

        async void listView_ItemSelected(System.Object sender, Xamarin.Forms.SelectedItemChangedEventArgs e)
        {
            if (e.SelectedItem != null)
            {
                string action = await DisplayActionSheet("Actions", "Cancel", null, "Edit", "Done", "Delete");

                switch (action)
                {
                    case "Edit":
                        await Navigation.PushAsync(new TaskEntryPage
                        {
                            BindingContext = e.SelectedItem as Task
                        });
                        break;
                    case "Done":
                        await App.Database.DeleteNoteAsync(e.SelectedItem as Task);
                        base.OnAppearing();
                        listView.ItemsSource = await App.Database.GetNotesAsync();
                        break;
                    case "Delete": 
                        await App.Database.DeleteNoteAsync(e.SelectedItem as Task);
                        base.OnAppearing();
                        listView.ItemsSource = await App.Database.GetNotesAsync();
                        break;
                }
                ((ListView)sender).SelectedItem = null;

                /*
                await Navigation.PushAsync(new TaskEntryPage
                {
                    BindingContext = e.SelectedItem as Task
                });
                */
            }
        }
    }
}
