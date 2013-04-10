package org.omships.omships;

public class TwitterItem extends FeedItem {
	public TwitterItem(FeedItem item){
		this.setTitle(item.getTitle());
		this.setDescription(item.getDescription());
		this.setPubDate(item.getPubDate());
		this.setLink(item.getLink());
	}
	@Override
	public int getImageResource() {
		return R.drawable.bird;
	}
}//end class TwitterItem
